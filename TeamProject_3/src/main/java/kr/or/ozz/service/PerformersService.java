package kr.or.ozz.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ozz.dto.PerformersDTO;

public interface PerformersService  {

    // Performers ������ �߰�
    public int insertPerformers(PerformersDTO dto);

    // Ư�� ������ Performers ������ ��ȸ
    public List<PerformersDTO> getPerfomersList(String userid);

    public List<PerformersDTO> getPerfomersEndList(String userid);

    public List<PerformersDTO> getoneMission(String userid);

}