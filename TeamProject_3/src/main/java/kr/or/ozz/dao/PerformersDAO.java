package kr.or.ozz.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ozz.dto.PerformersDTO;

public interface PerformersDAO {
	
    // Performers ������ �߰�
    public int insertPerformers(PerformersDTO dto);

    // Ư�� ������ Performers ������ ��ȸ
    public List<PerformersDTO> getPerfomersList(String userid);
    
    // ����� �̼� ����Ʈ ��ȸ
    public List<PerformersDTO> getPerfomersEndList(String userid);
    
    //�������� �̼� ����
    public List<PerformersDTO> missioningcnt(String userid);
    
    //�Ϸ�� �̼� ����
    public List<PerformersDTO> missionendcnt(String userid);
}
