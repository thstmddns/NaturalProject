# 라이브러리 설치
import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from sklearn.feature_extraction.text import TfidfVectorizer
import warnings; warnings.filterwarnings('ignore')
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.metrics import mean_squared_error
import re

# 미션페이지 추천
def mission_recommand(contents):
    print(f"origin contents{contents}")
    contents = [re.sub(r'[^\w\s]', ' ', content).split() for content in contents]
    contents = [word for words in contents for word in words]
    print(f"preprocessed contents{contents}")
    print(1)
    # 데이터 불러오기
    data = pd.read_csv('preprocess_inflearn_data.csv', delimiter=',')
    hwangjae_data = pd.read_csv('hwangjae_inflearn_data.csv', delimiter=',')
    data = data.drop('Unnamed: 0', axis=1)
    
    # 컬럼명 설정
    data.columns = ['Index', 'Title', 'Content', 'Level', 'Tag', 'Category', 'Author']
    hwangjae_data.columns = ['Index', 'Title', 'Content', 'Level', 'Tag', 'Category', 'Author']
    
    course = pd.read_csv('preprocess_inflearn_data.csv', delimiter=',')

    # content 기준
    count_vect = CountVectorizer(min_df = 1, ngram_range=(1, 2))
    content_mat = count_vect.fit_transform(course['Content'])
    content_sim = cosine_similarity(content_mat, content_mat)
    content_sim_sorted_ind = content_sim.argsort()[:, ::-1]
    def find_sim_course(df, sorted_ind, content_names, top_n = 10):
  # title 칼럼이 입력된 title_name 값인 DataFrame추출
        content_course = df[df['Tag'].apply(lambda x: any(content in x for content in content_names))]
        content_index = content_course.index.values
        similar_indexes = sorted_ind[content_index, :(top_n)]
        similar_indexes = similar_indexes.reshape(-1)

        return df.iloc[similar_indexes]
    
    similar_course = find_sim_course(course, content_sim_sorted_ind, contents, 5)
    similar_course_list = []
    course["Content"] = hwangjae_data["Content"]

    # similar_course_list = {"Title" : similar_course['Title'][:10].values.tolist(), "Content" : similar_course[["Content"]][:10].values.tolist(), "Tag" : similar_course[['Tag']][:10].values.tolist(), "Author" : similar_course[['Author']][:10].values.tolist()}
    similar_course_list = {"MISSION_NO" : similar_course['Index'][:3].values.tolist()}

    print(similar_course_list)
    return similar_course_list