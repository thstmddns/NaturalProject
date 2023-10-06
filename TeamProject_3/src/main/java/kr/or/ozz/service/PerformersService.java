package kr.or.ozz.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ozz.dto.PerformersDTO;

public interface PerformersService  {

    // Performers ������ �߰�
    public int insertPerformers(PerformersDTO performersDTO);

    // Ư�� ������ Performers ������ ��ȸ
    public List<PerformersDTO> getPerformersByUserId(String userid);

    // ���� ��¥�� ���� ��¥�� Performers ������ ��ȸ
    public List<PerformersDTO> getPerformersByDateRange(Date started_at, Date completed_at);

    // Ư�� ������ ���� ���� ������ Performers ������ ��ȸ
    public List<PerformersDTO> getPerformersByInProgressStep(int step_no);

    // Ư�� ������ Ư�� �̼��� Performers ������ ��ȸ
    public List<PerformersDTO> getPerformersByUserAndMission(String userid, int mission_no);
    
    // Ư�� ������ ���� ���� �׽�ũ ���� ��ȸ
    public List<PerformersDTO> getInProgressTasks(String userid, int task_no);
    
    // Ư�� ������ ���� ���� �׽�ũ ���� ������Ʈ
    public int updateInProgressTask(PerformersDTO dto);
    
    // Ư�� ������ �޼��� ���� ��ȸ
    public List<PerformersDTO> getUserAchievement(@Param("userid") String userid);
}