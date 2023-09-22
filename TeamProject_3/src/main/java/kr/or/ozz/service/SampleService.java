package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.SampleDTO;

public interface SampleService {

	public List <SampleDTO> getList();

	public int totalRecord(PagingDTO pDTO);

	public void hitCount(int no);

	public SampleDTO getView(int no);

}
