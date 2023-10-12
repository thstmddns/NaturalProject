package kr.or.ozz.dto;

public class PagingDTO {
   private int nowPage = 1; // 현재페이지
   private int onePageRecord = 5; // 한 페이지에 표시할 레코드 수
   private int m_totalRecord; // 미션 총 레코드 수
   private int q_totalRecord; // QnA 총 레코드 수
   private int r_totalRecord; // Review 총 레코드 수
   private int b_totalRecord; // Board 총 레코드 수
   private int u_totalRecord; // User 총 레코드 수
   private int totalPage; //총 페이지수
   
   private int onePageNumCount = 5; //한 페이지에 표시되는 페이지 수
   private int startPageNum = 1; //시작페이지
   
   private int lastPageRecord = 5; //마지막 페이지에 남아있는 레코드 수
   
   private String searchKey; //검색키
   private String searchWord; //검색어
   
   public int getNowPage() {
      return nowPage;
   }
   public void setNowPage(int nowPage) {
      this.nowPage = nowPage;
      
      //페이지의 시작번호 생성하기
      //시작번호 = ((현재페이지 - 1)/한페이지에 표시할 페이지 수)*한페이지에 표시할 페이지 수 + 1;
      startPageNum = ((nowPage-1) / onePageNumCount)*onePageNumCount + 1;
   }
   public int getOnePageRecord() {
      return onePageRecord;
   }
   public void setOnePageRecord(int onePageRecord) {
      this.onePageRecord = onePageRecord;
   }
   public int getTotalPage() {
      return totalPage;
   }
   public void setTotalPage(int totalPage) {
      this.totalPage = totalPage;
   }
   public int getOnePageNumCount() {
      return onePageNumCount;
   }
   public void setOnePageNumCount(int onePageNumCount) {
      this.onePageNumCount = onePageNumCount;
   }
   public int getStartPageNum() {
      return startPageNum;
   }
   public void setStartPageNum(int startPageNum) {
      this.startPageNum = startPageNum;
   }
   public int getLastPageRecord() {
      return lastPageRecord;
   }
   public void setLastPageRecord(int lastPageRecord) {
      this.lastPageRecord = lastPageRecord;
   }
   public String getSearchKey() {
      return searchKey;
   }
   public void setSearchKey(String searchKey) {
      this.searchKey = searchKey;
   }
   public String getSearchWord() {
      return searchWord;
   }
   public void setSearchWord(String searchWord) {
      this.searchWord = searchWord;
   }
   
   public int getM_totalRecord() {
      return m_totalRecord;
   }
   public void setM_totalRecord(int m_totalRecord) {
      this.m_totalRecord = m_totalRecord;
      totalPage = (int)Math.ceil((double)m_totalRecord/onePageRecord);
      lastPageRecord = onePageRecord; //5
      if(totalPage==nowPage) {
         if(m_totalRecord%onePageRecord!=0) {
            lastPageRecord = m_totalRecord % onePageRecord; //1,2,3,4 중
         }
      }
   }
   public int getQ_totalRecord() {
      return q_totalRecord;
   }
   public void setQ_totalRecord(int q_totalRecord) {
      this.q_totalRecord = q_totalRecord;
      totalPage = (int)Math.ceil((double)q_totalRecord/onePageRecord);
      lastPageRecord = onePageRecord; //5
      if(totalPage==nowPage) {
         if(q_totalRecord%onePageRecord!=0) {
            lastPageRecord = q_totalRecord % onePageRecord; //1,2,3,4 중
         }
      }
   }
   public int getR_totalRecord() {
      return r_totalRecord;
   }
   public void setR_totalRecord(int r_totalRecord) {
      this.r_totalRecord = r_totalRecord;
      totalPage = (int)Math.ceil((double)r_totalRecord/onePageRecord);
      lastPageRecord = onePageRecord; //5
      if(totalPage==nowPage) {
         if(r_totalRecord%onePageRecord!=0) {
            lastPageRecord = r_totalRecord % onePageRecord; //1,2,3,4 중
         }
      }
   }
   public int getB_totalRecord() {
      return b_totalRecord;
   }
   public void setB_totalRecord(int b_totalRecord) {
      this.b_totalRecord = b_totalRecord;
      totalPage = (int)Math.ceil((double)b_totalRecord/onePageRecord);
      lastPageRecord = onePageRecord; //5
      if(totalPage==nowPage) {
         if(b_totalRecord%onePageRecord!=0) {
            lastPageRecord = b_totalRecord % onePageRecord; //1,2,3,4 중
         }
      }
   }
   public int getU_totalRecord() {
      return u_totalRecord;
   }
   public void setU_totalRecord(int u_totalRecord) {
      this.u_totalRecord = u_totalRecord;
      totalPage = (int)Math.ceil((double)u_totalRecord/onePageRecord);
      lastPageRecord = onePageRecord; //5
      if(totalPage==nowPage) {
         if(u_totalRecord%onePageRecord!=0) {
            lastPageRecord = u_totalRecord % onePageRecord; //1,2,3,4 중
         }
      }
   }
   
   /*
    * public int getTotalRecord() { return totalRecord; } public void
    * setTotalRecord(int totalRecord) { this.totalRecord = totalRecord;
    * 
    * //총페이지수 계산하기 16 -> 4, 15 -> 3 // 3.44444 3.000 // ceil():올림, round():반올림,
    * floor():버림 totalPage = (int)Math.ceil((double)totalRecord/onePageRecord);
    * 
    * //마지막페이지에 남아있는 레코드 수 lastPageRecord = onePageRecord; //5
    * if(totalPage==nowPage) { if(totalRecord%onePageRecord!=0) { lastPageRecord =
    * totalRecord % onePageRecord; //1,2,3,4 중 } } }
    */
}