package kr.or.ozz.dto;

public class PagingDTO {
   private int nowPage = 1;         
   private int onePageRecord = 15;      
   
   private int totalRecord;         
   private int totalPage;            
   
   private int onePageNumCount = 5;   
   private int startPageNum = 1;      
   
   private int lastPageRecord = 15;      
   
   private String searchKey;         

   private String searchWord;         
   
   public int getNowPage() {
      return nowPage;
   }
   public void setNowPage(int nowPage) {
      this.nowPage = nowPage;

      startPageNum = ((nowPage-1) / onePageNumCount ) * onePageNumCount + 1;
   }
   public int getOnePageRecord() {
      return onePageRecord;
   }
   
   public void setOnePageRecord(int onePageRecord) {
      this.onePageRecord = onePageRecord;
   }

   public int getTotalRecord() {
      return totalRecord;
   }

   public void setTotalRecord(int totalRecord) {
      this.totalRecord = totalRecord;
   
      // Ï¥? ?éò?ù¥Ïß? ?àò Í≥ÑÏÇ∞?ïòÍ∏?
      // ceil():?ò¨Î¶?, round():Î∞òÏò¨Î¶?, floor():?Ç¥Î¶?
      totalPage = (int)Math.ceil((double)totalRecord / onePageRecord);
      
      // ÎßàÏ?Îß? ?éò?ù¥Ïß??ùò ?Ç®?ïÑ?ûà?äî ?†àÏΩîÎìú ?àò 
      lastPageRecord = onePageRecord;
      if(totalPage == nowPage) {
         if(totalRecord % onePageRecord != 0) {
            lastPageRecord = (totalRecord % onePageRecord);
         }
      }
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
   
}