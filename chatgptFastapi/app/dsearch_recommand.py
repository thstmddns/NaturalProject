# 라이브러리 설치
import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from sklearn.feature_extraction.text import TfidfVectorizer
import warnings; warnings.filterwarnings('ignore')
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.metrics import mean_squared_error

# 미션페이지 추천
def search_recommand(search):
    print(1)
    # 데이터 불러오기
    data = pd.read_csv('preprocess_inflearn_data.csv', delimiter=',')
    data = data.drop('Unnamed: 0', axis=1)
    
    # 컬럼명 설정
    data.columns = ['Index', 'Title', 'Content', 'Level', 'Tag', 'Category', 'Author']
    
    # 임의의 R행렬 설정
    R = np.array([[4, np.NaN, np.NaN, 2, np.NaN ],
              [np.NaN, 5, np.NaN, 3, 1 ],
              [np.NaN, np.NaN, 3, 4, 4 ],
              [5, 2, 1, 2, np.NaN ]])
    print(4)
    num_users, num_items = R.shape
    K=3
    print(5)

    # P와 Q 매트릭스의 크기를 지정하고 정규분포를 가진 random한 값으로 입력합니다.
    np.random.seed(1)
    P = np.random.normal(scale=1./K, size=(num_users, K))
    Q = np.random.normal(scale=1./K, size=(num_items, K))
    
    def get_rmse(R, P, Q, non_zeros):

        error = 0
        # 두개의 분해된 행렬 P와 Q.T의 내적으로 예측 R 행렬 생성
        full_pred_matrix = np.dot(P, Q.T)
  
        # 실제 R 행렬에서 널이 아닌 값의 위치 인덱스 추출하여 실제 R 행렬과 예측 행렬의 RMSE 추출
        x_non_zero_ind = [non_zero[0] for non_zero in non_zeros]
        y_non_zero_ind = [non_zero[1] for non_zero in non_zeros]
        R_non_zeros = R[x_non_zero_ind, y_non_zero_ind]
        full_pred_matrix_non_zeros = full_pred_matrix[x_non_zero_ind, y_non_zero_ind]
        mse = mean_squared_error(R_non_zeros, full_pred_matrix_non_zeros)
        rmse = np.sqrt(mse)
        return rmse

    
    # R > 0 인 행 위치, 열 위치, 값을 non_zeros 리스트에 저장.
    non_zeros = [ (i, j, R[i,j]) for i in range(num_users) for j in range(num_items) if R[i, j] > 0 ]

    steps=1000
    learning_rate=0.01
    r_lambda=0.01
    
    # SGD 기법으로 P와 Q 매트릭스를 계속 업데이트.
    pred_matrix = np.dot(P, Q.T)
    course = pd.read_csv('preprocess_inflearn_data.csv', delimiter=',')
    
    # content 기준
    count_vect = CountVectorizer(min_df = 1, ngram_range=(1, 2))
    content_mat = count_vect.fit_transform(course['Content'])
    content_sim = cosine_similarity(content_mat, content_mat)
    content_sim_sorted_ind = content_sim.argsort()[:, ::-1]
    def find_sim_course(df, sorted_ind, content_names, top_n = 10):
  # title 칼럼이 입력된 title_name 값인 DataFrame추출
        content_course = df[df['Content'].apply(lambda x: any(content in x for content in content_names))]
        content_index = content_course.index.values
        similar_indexes = sorted_ind[content_index, :(top_n)]
        similar_indexes = similar_indexes.reshape(-1)

        return df.iloc[similar_indexes]
    
    similar_course = find_sim_course(course, content_sim_sorted_ind, search, 5)
    similar_course_list = []
    similar_course_list = {"Title" : similar_course['Title'][:10].values.tolist(), "Content" : similar_course[["Content"]][:10].values.tolist(), "Tag" : similar_course[['Tag']][:10].values.tolist(), "Author" : similar_course[['Author']][:10].values.tolist()}
    print(similar_course_list)
    return similar_course_list