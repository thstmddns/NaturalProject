<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${logStatus=='Y'}">
	<p>로그인중</p>
</c:if>
<div class="card-body bg-white mt-0 shadow">
    <p style="font-weight: bold">카카오페이 현재 사용가능</p>
    <label class="box-radio-input"><input type="radio" name="cp_item" value="10000"><span>1개월 : 10000원</span></label>
    <label class="box-radio-input"><input type="radio" name="cp_item" value="30000"><span>3개월 : 30000원</span></label>
    <label class="box-radio-input"><input type="radio" name="cp_item" value="50000"><span>6개월 : 50000원</span></label>
    <label class="box-radio-input"><input type="radio" name="cp_item" value="100000"><span>12개월 : 100000원</span></label>
    <button type="button" class="btn btn-lg btn-block btn-custom" id="charge_kakao">결제하기</button>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script>
$('#charge_kakao').click(function () {
    // getter
    var IMP = window.IMP;
    IMP.init('imp41876435');

    var buyerName = '${sessionScope.logId}';
	var payamount = $('input[name="cp_item"]:checked').val();
	
	// 선택된 라디오 버튼의 value에 따라 주문명 동적으로 설정
    var suboption = '';
    switch (payamount) {
        case '10000':
        	suboption = '1개월';
            break;
        case '30000':
        	suboption = '3개월';
            break;
        case '50000':
        	suboption = '6개월';
            break;
        case '100000':
        	suboption = '12개월';
            break;
        default:
        	suboption = '기타';
    }
	
    // 결제창 호출 코드
    IMP.request_pay({
        pg: "kakaopay",
        
        merchant_uid: 'merchant_' + new Date().getTime(),

        name: suboption,
        amount: payamount,
        buyer_email: "chg30528@naver.com",
        buyer_name: buyerName, // 아이디 정보 사용
        buyer_tel: "010-1234-5678",
        buyer_addr: "서울",
        buyer_postcode: '123-456'
    }, function (rsp) {
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '결제 금액 : ' + rsp.paid_amount + '원';
            
            $.ajax({
                type: "POST", 
                url: '${pageContext.request.contextPath}/mypage/paymentRequest',
                data: {
                	"pay_amount" : payamount,
                	"sub_option" : suboption,
                	
                  },
                success: function (result) {
                    if (result == "failure") {
                        alert("결제 실패");
                        window.location.reload();
                    } else {
                        /* var form = $('<form method="post" action="${pageContext.request.contextPath}/mypage/subscription_list">');
                        form.append($('<input type="hidden" name="userid" value="' + result + '">'));
                        $('body').append(form);
                        form.submit(); */
                        
                        /*get방식으로 경로 설정*/
                    	window.location.href= "${pageContext.request.contextPath}/mypage/subscription_list";
                    }
                },
                error: function (e) {
                    console.log(e.responseText);
                }             
            });
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
});
</script>
</body>
</html>
