package kr.or.smhrd.dao;

import java.util.List;

import kr.or.smhrd.dto.PagingDTO;
import kr.or.smhrd.dto.SampleDTO;

public interface SampleDAO {

	public List <SampleDTO> getList();

	public int totalRecord(PagingDTO pDTO);

	public void hitCount(int no);

	public SampleDTO getView(int no);

}
