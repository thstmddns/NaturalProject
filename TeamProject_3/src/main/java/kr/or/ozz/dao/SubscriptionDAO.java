package kr.or.ozz.dao;

import java.util.List;

import kr.or.ozz.dto.SubscriptionDTO;

public interface SubscriptionDAO {


    // ��� ���� ������ ��ȸ�ϴ� �޼���
    List<SubscriptionDTO> getAllSubscriptions(String logId);

    // ��Ÿ ���� ���� �޼��带 �ʿ信 ���� �߰��� �� �ֽ��ϴ�.
}
	

