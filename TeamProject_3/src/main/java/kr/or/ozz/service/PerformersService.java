package kr.or.ozz.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ozz.dto.PerformersDTO;

public interface PerformersService  {

    // Performers 데이터 추가
    public int insertPerformers(PerformersDTO performersDTO);

    // 특정 유저의 Performers 데이터 조회
    public List<PerformersDTO> getPerformersByUserId(String userid);

    // 시작 날짜와 종료 날짜로 Performers 데이터 조회
    public List<PerformersDTO> getPerformersByDateRange(Date started_at, Date completed_at);

    // 특정 유저의 진행 중인 스텝의 Performers 데이터 조회
    public List<PerformersDTO> getPerformersByInProgressStep(int step_no);

    // 특정 유저의 특정 미션의 Performers 데이터 조회
    public List<PerformersDTO> getPerformersByUserAndMission(String userid, int mission_no);
    
    // 특정 유저의 진행 중인 테스크 정보 조회
    public List<PerformersDTO> getInProgressTasks(String userid, int task_no);
    
    // 특정 유저의 진행 중인 테스크 정보 업데이트
    public int updateInProgressTask(PerformersDTO dto);
    
    // 특정 유저의 달성률 정보 조회
    public List<PerformersDTO> getUserAchievement(@Param("userid") String userid);
}