from fastapi import FastAPI, File, UploadFile, Form, HTTPException, Request
from pydantic import BaseModel
from utils import *
from dlanding_recommand import *
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware
from pathlib import Path
import time
import shutil

#initialize fast api
app  = FastAPI()

# CORS 미들웨어 추가
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 모든 출처 허용. 실제 배포 시에는 필요한 도메인만 허용해야 합니다.
    allow_credentials=True,
    allow_methods=["*"],  # 모든 HTTP 메소드 허용
    allow_headers=["*"],  # 모든 HTTP 헤더 허용
)
tokenizer, model, device = initializeModels()  

class DescriptionInput(BaseModel):
    title: str
    pdf_path: str

@app.post("/generate_description")
async def generate_description(input: DescriptionInput):
    print(input.pdf_path)
    try:
        start = time.time()
        # PDF 파일일 경우 텍스트를 추출
        if input.pdf_path.split(".")[-1] == "pdf":
            # 텍스트 추출
            result = extractTextFromPdf(input.pdf_path)
            print('pdf로부터 텍스트 추출 완료')
            
            # 텍스트 전처리
            preprocessedText = preprocessPdfText(result)
            print('pdf 텍스트 전처리 완료')

        # word 파일일 경우 텍스트 추출
        elif (input.pdf_path.split('.')[-1] == "docx") or (input.pdf_path.split('.')[-1] == "doc"):
             # 텍스트 추출
            result = extractTextFromDocx(input.pdf_path)
            print('word로부터 텍스트 추출 완료')
            
            # 텍스트 전처리
            preprocessedText = preprocessDocxText(result)
            print('word 텍스트 전처리 완료')

        # 정제된 텍스트를 바탕으로 요약 생성
        generatedSummary = returnSummary(preprocessedText, tokenizer, model, device)
        print('요약 생성 완료')

        # 요약된 텍스트와 제목을 함께 ChatGPT에 전달하여 설명 생성
        description = generateGo(f"제목: {input.title}, 내용: {generatedSummary}")  # utils 함수 사용
        print("업무 프로세스 생성 완료")
        print(f'종료시간 : {time.time() - start}')
        print(description)
        return JSONResponse(content=description)
    
    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=400)
    
class landingInput(BaseModel):
    concern : str    
    
@app.post("/dlanding_recommand")
async def dlanding_recommand(input : landingInput):
    l_recommand = landing_recommand(input.concern)
    return l_recommand