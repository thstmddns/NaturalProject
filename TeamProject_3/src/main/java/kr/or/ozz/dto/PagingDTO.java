package kr.or.ozz.dto;

public class PagingDTO {

   private int nowPage = 1; // ����������
   private int onePageRecord = 5; // �� �������� ǥ���� ���ڵ� ��
   private int m_totalRecord; // �̼� �� ���ڵ� ��
   private int q_totalRecord; // QnA �� ���ڵ� ��
   private int r_totalRecord; // Review �� ���ڵ� ��
   private int b_totalRecord; // Board �� ���ڵ� ��
   private int u_totalRecord; // User �� ���ڵ� ��
   private int totalPage; //�� ��������
   
   private int onePageNumCount = 5; //�� �������� ǥ�õǴ� ������ ��
   private int startPageNum = 1; //����������
   
   private int lastPageRecord = 5; //������ �������� �����ִ� ���ڵ� ��
   
   private String searchKey; //�˻�Ű
   private String searchWord; //�˻���
   
   public int getNowPage() {
      return nowPage;
   }
   public void setNowPage(int nowPage) {
      this.nowPage = nowPage;
      
      //�������� ���۹�ȣ �����ϱ�
      //���۹�ȣ = ((���������� - 1)/���������� ǥ���� ������ ��)*���������� ǥ���� ������ �� + 1;
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
            lastPageRecord = m_totalRecord % onePageRecord; //1,2,3,4 ��
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
            lastPageRecord = q_totalRecord % onePageRecord; //1,2,3,4 ��
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
            lastPageRecord = r_totalRecord % onePageRecord; //1,2,3,4 ��
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
            lastPageRecord = b_totalRecord % onePageRecord; //1,2,3,4 ��
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
            lastPageRecord = u_totalRecord % onePageRecord; //1,2,3,4 ��
         }
      }
   }
   
   /*
    * public int getTotalRecord() { return totalRecord; } public void
    * setTotalRecord(int totalRecord) { this.totalRecord = totalRecord;
    * 
    * //���������� ����ϱ� 16 -> 4, 15 -> 3 // 3.44444 3.000 // ceil():�ø�, round():�ݿø�,
    * floor():���� totalPage = (int)Math.ceil((double)totalRecord/onePageRecord);
    * 
    * //�������������� �����ִ� ���ڵ� �� lastPageRecord = onePageRecord; //5
    * if(totalPage==nowPage) { if(totalRecord%onePageRecord!=0) { lastPageRecord =
    * totalRecord % onePageRecord; //1,2,3,4 �� } } }
    */

}