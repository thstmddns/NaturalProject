package kr.or.ozz.service;

import java.util.List;

import kr.or.ozz.dto.SubscriptionDTO;

public interface SubscriptionService {


    // ��� ���� ������ ��ȸ�ϴ� �޼���
    List<SubscriptionDTO> getAllSubscriptions(String logId);

    // ��Ÿ ���� ���� �޼��带 �ʿ信 ���� �߰��� �� �ֽ��ϴ�.
}
	

