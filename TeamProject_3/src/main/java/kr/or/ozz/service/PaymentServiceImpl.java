package kr.or.ozz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ozz.dao.PaymentDAO;
import kr.or.ozz.dto.PaymentDTO;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO dao;

    @Override
    public int GetPayment(String userid) {
        return dao.GetPayment(userid);
    }

    @Override
    public void insertPayment(PaymentDTO dto) {
        dao.insertPayment(dto);
    }

    @Override
    public List<PaymentDTO> getUserPayment(String userid) {
        return dao.getUserPayment(userid);
    }

}
