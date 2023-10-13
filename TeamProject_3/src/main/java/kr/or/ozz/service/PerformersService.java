package kr.or.ozz.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ozz.dto.PerformersDTO;

public interface PerformersService  {

    // Performers 데이터 추가
    public int insertPerformers(PerformersDTO dto);

    // 특정 유저의 Performers 데이터 조회
    public List<PerformersDTO> getPerfomersList(String userid);

    public List<PerformersDTO> getPerfomersEndList(String userid);

    public List<PerformersDTO> getoneMission(String userid);

}