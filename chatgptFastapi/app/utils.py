from konlpy.tag import Okt
from transformers import BartForConditionalGeneration, PreTrainedTokenizerFast, set_seed
from pdf2image import convert_from_path
from timm.data import resolve_data_config
from timm.data.transforms_factory import create_transform
from konlpy.tag import Okt
from concurrent.futures import ThreadPoolExecutor
from pdf2image import convert_from_path
from PIL import Image
from docx import Document

import openai
import os
import easyocr
import io
import re
import torch
import tempfile
import peft
import pandas as pd
import timm

def initializeModels(modelName="yoonjae97/kobart_AdamW_80000"):
    """
        모델과 토크나이저를 초기화 합니다.
        model_name으로는 "hyunwoongko/kobart" 기본 모델
        "yoonjae97/kobart_Adam_40000", "yoonjae97/kobart_Adam_80000", 
        "yoonjae97/kobart_AdamW_40000", "yoonjae97/kobart_AdamW_80000"
        위의 4가지가 있으며 kobart 모델을 파인튜닝할때 사용된 옵티마이저와 데이터 개수를 의미합니다.
        Args:
            model_name (str, optional): 사용할 모델의 이름. 기본값은 "yoonjae97/kobart_AdamW_80000".

        Returns:
            tokenizer: 토크나이저 객체
            
            model: 모델 객체

    """
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

    tokenizer = PreTrainedTokenizerFast.from_pretrained("hyunwoongko/kobart")

    model = BartForConditionalGeneration.from_pretrained(modelName).to(device)

    return tokenizer, model, device
set_seed(42)
#Set your OpenAI API key
openai.api_key="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"

# EasyOCR 리더 및 모델 초기화
reader = easyocr.Reader(['ko', 'en'], gpu=True)

okt = Okt()


# 워드파일일 경우 텍스트 추출 함수
def extractTextFromDocx(docxPath):
    """
        워드 파일로 부터 텍스트를 추출합니다
        Args:
            docxPath : 워드 파일의 경로를 입력해주세요

        Returns:
            validParagraphs : [제목, 내용] 형식의 데이터가 담긴 2중 리스트 구조

    """
    currentParagraph = ''
    doc = Document(docxPath)
    paragraphs = []
    currentTitle = None

    for p in doc.paragraphs: 
        text = p.text.strip() 
        if not text: 
            # 빈 줄을 만나면 현재까지의 제목과 본문을 저장
            if currentTitle and currentParagraph:
                paragraphs.append([currentTitle, currentParagraph])
            currentTitle = None  # 제목 초기화
            currentParagraph = ""  # 본문 초기화
        else:
            if not currentTitle:  # 제목이 아직 없는 경우
                currentTitle = '문단 소제목 : ' + text
            else:
                currentParagraph += text + "\n"

    # 마지막 문단은 주로 출처, 참고자료 등의 불필요한 데이터일 가능성이 높아 제거
    paragraphs.pop()

    # 글자수가 20글자 이상인 경우만 저장
    # 잘못 구분됐거나 paragraph의 내용이 너무 적은 경우는 일종의 이상치라 판단하여 제외함
    validParagraphs = [(title, paragraph) for title, paragraph in paragraphs if len(paragraph) >= 20]

    return validParagraphs

# word에서 추출한 데이터 전처리 함수
# 다르게 한 이유는 word파일은 pdf와 달리 추출되는 텍스트가 훨씬 더 깨끗한 형태
# 따라서 문단별로 소제목을 보존할 수 있어서 pdf용의 preprocessText 함수를 사용 후 제목과 내용을 붙여서 반환하는게 다름
def preprocessDocxText(paragraphs):
    """
        워드용 전처리 실행 함수
        Args:
            pragraphs : extractTextFromDocx 함수를 통해 word에서 추출한 텍스트 데이터 리스트를 입력해주세요. 형태는 [제목, 내용]의 데이터가 여러개 담긴 2중 리스트여야 합니다.

        Returns:
            preprocessedTexts : 내용에 대해서만 전처리 함수를 실행한다음 제목과 내용을 하나의 str로 붙이고 리스트에 담아서 반환
    """
    def preprocessText(text):
        okt = Okt()

        # 정규표현식을 사용하여 특수 문자 및 숫자 제거
        text = re.sub(r'[^\\sㄱ-ㅎ가-힣]', '', text)

        # 형태소 분석 및 조사, 구두점 등 제거
        tokens = [word for word, pos in okt.pos(text) if pos not in ['Josa', 'Punctuation', 'Foreign']]

        # 불용어 처리
        stop_words = ['그', '이', '저']  # 불용어 리스트를 자신의 데이터에 맞게 구성
        tokens = [token for token in tokens if token not in stop_words]

        return ' '.join(tokens)
    preprocessedTexts = []
    # 내용에 대해서만 전처리 수행
    # 제목은 따로 저장
    textTitles = [i[0] for i in paragraphs]
    textContents = [preprocessText(i[1]) for i in paragraphs]
    
    # 제목과 내용을 붙여서 하나의 텍스트로 만들어서 저장
    for title, content in zip(textTitles, textContents):
        preprocessedTexts.append([title, content])

    return preprocessedTexts

# PDF 파일을 이미지로 변환하고 ocr을 이용해서 텍스트 추출하는 함수
def extractTextFromPdf(pdfPath):
    """
        pdf 파일로 부터 텍스트를 추출합니다
        Args:
            pdfPath : 워드 파일의 경로를 입력해주세요

        Returns:
            resultEasyocr : pdf로 부터 ocr을 이용해서 텍스트를 추출하고 페이지별로 나눠 저장한 리스트 반환

    """
    pdfImages = convert_from_path(pdfPath,500, poppler_path="C:\\workspaceSpring\\poppler-23.08.0\\Library\\bin")

    # EasyOCR 리더 초기화
    reader = easyocr.Reader(['ko', 'en'], gpu=True)  
    resultEasyocr = []

    # 각 이미지에서 텍스트 추출
    for image in pdfImages:
        # 이미지를 EasyOCR이 인식할 수 있는 형식으로 변환 (bytes-like object로)
        # io.BytesIO()는 메모리상에서 바이트 데이터를 다루기 위한 객체로 이미지 데이터를 임시 저장하기 위해 사용
        with io.BytesIO() as output: # with 구문은 파이썬에서 리소스 관리를 위해 사용, 코드 실행 시 리소스를 열고 종료 시 해제
            # pdfImages에서 가져온 이미지들은 어떤 형식인지 정해져 있지 않아 JPEG로 변환 후 저장
            image.save(output, format="JPEG") 
            # 메모리 상의 데이터를 바이트로 가져와서 저장
            imageData = output.getvalue()

        # 이미지를 EasyOCR을 통해 텍스트 추출
        extractedText = reader.readtext(imageData)

        # 추출된 텍스트를 하나의 문자열로 합치기
        pageText = ' '.join([detection[1] for detection in extractedText])

        # 결과에 추가
        resultEasyocr.append(pageText)
        
    return resultEasyocr

def preprocessPdfText(pragraphs):
    """
        pdf용 전처리 실행 함수
        Args:
            pragraphs : extractTextFromPdf 함수를 통해 pdf에서 추출한 텍스트 데이터 리스트를 입력해주세요

        Returns:
            preprocessedTexts : 각 텍스트 별로 전처리 함수를 실행하고 리스트로 반환합니다.
    """
    def preprocessText(text):
        okt = Okt()

        # 정규표현식을 사용하여 특수 문자 및 숫자 제거
        text = re.sub(r'[^\\sㄱ-ㅎ가-힣]', '', text)

        # 형태소 분석 및 조사, 구두점 등 제거
        tokens = [word for word, pos in okt.pos(text) if pos not in ['Josa', 'Punctuation', 'Foreign']]

        # 불용어 처리
        stop_words = ['그', '이', '저']  # 불용어 리스트를 자신의 데이터에 맞게 구성
        tokens = [token for token in tokens if token not in stop_words]

        return ' '.join(tokens)
    # 전처리
    preprocessedTexts = [preprocessText(i) for i in pragraphs]

    return preprocessedTexts

def returnSummary(preprocessedText, tokenizer, model, device):
    """ 
        워드용 전처리 실행 함수. 1~3번째에는 제목, 목차등의 정보가 있을 것이라고 가정하여 요약X,
        마지막은 대부분 출처, 감사합니다 등 불필요한 내용일 것이라고 가정하여 제거
        나머지 부분에 대해서만 요약 진행
        Args:
            preprocessedText : 각 파일 유형에 맞는 preprocess함수를 실행 후 반환된 리스트를 입력하세요.
            tokenizer : initialize_models에서 반환된 토크나이저를 입력
            model : initialize_models에서 반환된 모델을 입력


        Returns:
            result : 내용에 대해서만 전처리 함수를 실행한다음 제목과 내용을 하나의 str로 붙이고 리스트에 담아서 반환
    """
    result = []
    executor = ThreadPoolExecutor(max_workers=4)

    def generateSummary(text):
        input_ids = tokenizer(text, return_tensors="pt").input_ids.to(device)
        summary_ids = model.generate(input_ids, num_beams=5, length_penalty=1.7, max_length=130, early_stopping=True)
        summaryText = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
        return summaryText

    if isinstance(preprocessedText[0], list):
        titles = [i for i, _ in preprocessedText][:-1]
        texts = [j for _, j in preprocessedText]

        
        textsSummary = texts[:3] + list(executor.map(generateSummary, texts[3:-1]))

        for i, j in zip(titles, textsSummary):
            temp = i + " 문단 내용 : " + j
            result.append(temp)

    else:

        result = preprocessedText[:3] + list(executor.map(generateSummary, preprocessedText[3:-1]))

    return " ".join(result)

def extract_analysis_steps(data):

    # 규칙 1
    pattern = r"(.*?)출력 형식"
    result = re.search(pattern, data, re.DOTALL)
    if result:
        data = result.group(1)
    if '위의 단계는' in data.rsplit('\n', 1)[-1]:
        data = data.rsplit('\n', 1)[0]
    elif '위의 단계는' in data.rsplit('\n\n', 1)[-1]:
        data = data.rsplit('\n\n', 1)[0]
    if '업무 프로세스' in data.rsplit('\n', 1)[-1]:
        data = data.rsplit('\n', 1)[0]
    elif '업무 프로세스' in data.rsplit('\n\n', 1)[-1]:
        data = data.rsplit('\n\n', 1)[0]

    # 규칙 2
    data = data.split('단계')[1:]
    results = []
    for step in data:

        # 규칙 3    
        step_name = step.split('\n', 1)[0].split(':')[1].strip()
        step_name = step_name.replace(']', '').replace(',', '')

        # 규칙 4
        tasks_txt = step.split('\n', 1)[1]
        if '\n - ' in tasks_txt:
            tasks = tasks_txt.split('\n -')
        else:
            tasks = tasks_txt.split('\n-')

        # 규칙 5
        task_lst = []
        for task in tasks:
            if '**' in task:
                task_name = task.split('**')[0]
                task_name = task_name.split(':')[1].replace(',', '').replace('\n', '')
                task_code = task.split('**')[1]
                if '```' in task_code:
                    task_code = '```' + task_code.split('```', 2)[1] + '```'
                else:
                    task_code = task_code.replace('[', '').replace('\'', '').replace(',', '').strip()
            else:
                try:
                    task_name = task.split(':')[1].replace(',', '')
                    task_code = ""
                except:
                    pass
                task_code = ""
            task_name = task_name.strip()
            
            # 이상치 제거
            if len(task_name) >= 30:
                continue
            if len(task_name) == 0:
                continue
            
            if task_code != "":
                task_lst.append({"task_name":task_name, 'task_code':task_code})
            else:
                task_lst.append({"task_name":task_name})
            task_name = ''
            task_code = ''
        step_result = {
                "단계": step_name,
                "태스크":task_lst

        }

        results.append(step_result)
    return results

def generateGo(input):

    messages=[
        {"role":"user",
         "content": """
         - 제목과 요약본을 보고, 필요한 단계는 너가 텍스트를 읽고 한번 정해서 업무 프로세스를 생성해봐.
         - 업무 프로세스는 여러 개의 단계와 각 단계별로 여러개의 세부 업무들을 가지고 있어.
         - 각 단계별로 세부적으로 수행해야할 세부 업무들 2가지 이상, 그리고 세부 업무에 필요한 경우에만 간단한 예시코드를 생성해줘. 
         - 각 세부 업무에는 반드시 1가지의 업무만 들어가야해.
         - 예시코드는 실제로 존재하는 라이브러리의 코드를 기반해서 구체적으로 생성해줘.
         - 누락된 단계를 채우고, 불필요한 단계가 있는지 확인해 줘. 
         - 단계는 업무 프로세스 수행에 필요한 만큼 최소 5개 이상 최대 15개미만으로 가능한 상세하게 생성해줘
         - 출력 형식은 아래처럼 만들어줘
         - 출력 형식:
            [단계 : 제목, 
                - 세부업무 : 제목, 
                ** 위의 세부업무에 필요한 예시코드, 
                - 세부업무 : 제목, 
                ** 위의 세부업무에 필요한 예시코드,
                - 세부업무 : 제목, 
                ** 위의 세부업무에 필요한 예시코드], 
            [단계 : 제목, 
                - 세부업무 : 제목, 
                ** 위의 세부업무에 필요한 예시코드, 
                - 세부업무 : 제목, 
                ** 위의 세부업무에 필요한 예시코드,
                - 세부업무 : 제목, 
                ** 위의 세부업무에 필요한 예시코드]"""},
    ]
    messages.append({"role":"assistant", "content":f"{input}"})
    completion = openai.ChatCompletion.create(
        model="gpt-3.5-turbo-16k",
        messages=messages,
        temperature = 0.3
    )
    reply=completion.choices[0].message.content

    result = extract_analysis_steps(reply)
    print(result)
    return result
