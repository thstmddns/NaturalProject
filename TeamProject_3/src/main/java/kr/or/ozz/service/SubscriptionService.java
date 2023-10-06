package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.SubscriptionDTO;

public interface SubscriptionService {


    // 모든 구독 정보를 조회하는 메서드
    List<SubscriptionDTO> getAllSubscriptions();

    // 기타 구독 관련 메서드를 필요에 따라 추가할 수 있습니다.
}
	

