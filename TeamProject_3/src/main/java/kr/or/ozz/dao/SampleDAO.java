package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.SampleDTO;

public interface SampleDAO {

	public List <SampleDTO> getList();

	public int totalRecord(PagingDTO pDTO);

	public void hitCount(int no);

	public SampleDTO getView(int no);

}
