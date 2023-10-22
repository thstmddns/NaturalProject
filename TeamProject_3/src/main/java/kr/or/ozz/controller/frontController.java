package kr.or.ozz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ozz.dto.BoardDTO;
import kr.or.ozz.dto.ItemDTO;
import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.PerformersDTO;
import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.dto.UserDTO;
import kr.or.ozz.service.BoardService;
import kr.or.ozz.service.MissionService;
import kr.or.ozz.service.PerformersService;
import kr.or.ozz.service.QnaService;
import kr.or.ozz.service.ReviewService;
import kr.or.ozz.service.UserService;

@Controller
@RequestMapping("/main")
public class frontController {
	@Autowired
	   MissionService Mservice;
	   
	   @Autowired
	   QnaService Qservice;
	   
	   @Autowired
	   ReviewService Rservice;
	   
	   @Autowired
	   BoardService Bservice;
	   
	   @Autowired
	   UserService Uservice;
	   
	   @Autowired
	    PerformersService Pservice;
	
	@GetMapping("/login")
	public String login() {
		return "register/login";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "register/registerForm";
	}
	
	@GetMapping("/landing")
	public ModelAndView landing(HttpSession session) throws JsonProcessingException {
	   List<MissionDTO> MissionToplist = Mservice.MissionToplist();
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("MissionToplist", MissionToplist);

	   try {
		   // 세션에서 현재 사용자의 아이디를 가져옴
	       String userid = (String)session.getAttribute("logId");
	       
	       // 사용자 정보를 가져옵니다.
	       UserDTO user = Uservice.getUser(userid);
	       
	       // 사용자의 concern을 가져옵니다.
	       String concern = user.getConcern();
	       // concern을 concernList에 잠아줍니다.      
	       List<String> concernList = Arrays.asList(concern);
	       
	       // FastAPI 서비스의 URL을 정의합니다.
	       String fastApiUrl = "http://127.0.0.1:8000/dlanding_recommand";
	
	       // FastAPI로 전송할 데이터를 생성합니다.
	       Map<Object, Object> requestData = new HashMap<Object, Object>();
	       requestData.put("concern", concernList);
	       
	       
	       // HTTP 요청을 보내기 위한 RestTemplate을 만듭니다.
	       RestTemplate restTemplate = new RestTemplate();
	
	       // HTTP 헤더 설정
	       HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	
	       // HTTP 요청 엔티티 생성
	       HttpEntity<Map<Object,Object>> entity = new HttpEntity<Map<Object, Object>>(requestData, headers);
	
	       // FastAPI에 HTTP POST 요청을 보냅니다.
	       ResponseEntity<String> response = restTemplate.exchange(
	           fastApiUrl,
	           HttpMethod.POST,
	           entity,
	           String.class
	       );
	       System.out.println("FastAPI Response: " + response.getBody());
	
	       // FastAPI에서의 응답을 처리합니다.
	       ObjectMapper mapper = new ObjectMapper();
	       Map<String, Object> responseBody = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
	
	       System.out.println("리스트출력" + responseBody);
	       
	
	       // 현재 사용자의 달성률 정보를 가져옴 (예시: 사용자 아이디로 달성률 정보를 가져옴)
	       List<PerformersDTO> mymissionList = Pservice.getPerfomersList(userid);
	       //System.out.println("UserId from session: " + userid);
		   
	       // 모델에 데이터 추가
	       mav.addObject("mymissionList", mymissionList);
	       mav.addObject("responseBodyList", responseBody);
	
	       List<PerformersDTO> mymissioningCnt = Pservice.missioningcnt(userid);
	       List<PerformersDTO> mymissionendCnt = Pservice.missionendcnt(userid);
	       
	       mav.addObject("mymissioningCnt", mymissioningCnt);
	       mav.addObject("mymissionendCnt", mymissionendCnt);
	       mav.setViewName("main/landing");
		}catch (Exception e) {
			mav.setViewName("main/landing");
		}

       return mav; // 뷰 이름 설정
	}
	
	@GetMapping("/idSearch")
	public String idSearch() {
		return "register/idSearch";
	}
	
	@GetMapping("/pwSearch")
	public String pwSearch() {
		return "register/pwSearch";
	}

	@GetMapping("/mainMission")
	public ModelAndView MissionToplist(HttpSession session) {
	   List<MissionDTO> MissionToplist = Mservice.MissionToplist();
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("MissionToplist", MissionToplist);
	   try {
		   // 세션에서 현재 사용자의 아이디를 가져옴
	       String userid = (String)session.getAttribute("logId");
	       
	       // 사용자 정보를 가져옵니다.
	       UserDTO user = Uservice.getUser(userid);
	       
	       // 사용자의 concern을 가져옵니다.
	       String concern = user.getConcern();
	       // concern을 concernList에 잠아줍니다.      
	       List<String> concernList = Arrays.asList(concern);
	       
	       // FastAPI 서비스의 URL을 정의합니다.
	       String fastApiUrl = "http://127.0.0.1:8000/dlanding_recommand";
	
	       // FastAPI로 전송할 데이터를 생성합니다.
	       Map<Object, Object> requestData = new HashMap<Object, Object>();
	       requestData.put("concern", concernList);
	       
	       
	       // HTTP 요청을 보내기 위한 RestTemplate을 만듭니다.
	       RestTemplate restTemplate = new RestTemplate();
	
	       // HTTP 헤더 설정
	       HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	
	       // HTTP 요청 엔티티 생성
	       HttpEntity<Map<Object,Object>> entity = new HttpEntity<Map<Object, Object>>(requestData, headers);
	
	       // FastAPI에 HTTP POST 요청을 보냅니다.
	       ResponseEntity<String> response = restTemplate.exchange(
	           fastApiUrl,
	           HttpMethod.POST,
	           entity,
	           String.class
	       );
	       System.out.println("FastAPI Response: " + response.getBody());
	
	       // FastAPI에서의 응답을 처리합니다.
	       ObjectMapper mapper = new ObjectMapper();
	       Map<String, Object> responseBody = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
	
	       System.out.println("리스트출력" + responseBody);
	       
	
	       // 현재 사용자의 달성률 정보를 가져옴 (예시: 사용자 아이디로 달성률 정보를 가져옴)
	       List<PerformersDTO> mymissionList = Pservice.getPerfomersList(userid);
	       //System.out.println("UserId from session: " + userid);
		   
	       // 모델에 데이터 추가
	       mav.addObject("mymissionList", mymissionList);
	       mav.addObject("responseBodyList", responseBody);
	
	       List<PerformersDTO> mymissioningCnt = Pservice.missioningcnt(userid);
	       List<PerformersDTO> mymissionendCnt = Pservice.missionendcnt(userid);
	       
	       mav.addObject("mymissioningCnt", mymissioningCnt);
	       mav.addObject("mymissionendCnt", mymissionendCnt);
	       mav.setViewName("main/mission");
		}catch (Exception e) {
			mav.setViewName("main/mission");
		} 
	   return mav;
	}
	
	@GetMapping("/mainMaster")
	public String mainMa() {
		return "main/master";
	}
	
	@GetMapping("/mainCom")
	public String mainCommu() {
		return "main/community";
	}
	
	@GetMapping("/comWrite")
	public String comWrite() {
		return "community/comWrite";
	}
	
	@GetMapping("/QnAWrite")
	public String QnAWrite() {
		return "mission/QnA";
	}
	
	@GetMapping("/feedback")
	public String feedbackWrite() {
		return "mission/feedback";
	}
	

	@GetMapping("/searchCom")
	public ModelAndView searchCom(PagingDTO pDTO) {
		pDTO.setM_totalRecord(Mservice.m_totalRecord(pDTO));
		pDTO.setQ_totalRecord(Qservice.q_totalRecord(pDTO));
		pDTO.setR_totalRecord(Rservice.r_totalRecord(pDTO));
		pDTO.setB_totalRecord(Bservice.b_totalRecord(pDTO));
		pDTO.setU_totalRecord(Uservice.u_totalRecord(pDTO));
		
		List<QnaDTO> Q_list = Qservice.Qnalist(pDTO);
		List<ReviewDTO> R_list = Rservice.Reviewlist(pDTO);
		List<BoardDTO> B_list = Bservice.Boardlist(pDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("Q_list", Q_list);
		mav.addObject("R_list", R_list);
		mav.addObject("B_list", B_list);
		mav.addObject("pDTO", pDTO);
		
		mav.setViewName("main/searchCom");
		return mav;
	}
	
	@GetMapping("/searchMission")
	public ModelAndView searchMission(@RequestParam(name = "searchWord", required = false) String searchWord, PagingDTO pDTO) throws JsonProcessingException {
		pDTO.setM_totalRecord(Mservice.m_totalRecord(pDTO));
		pDTO.setQ_totalRecord(Qservice.q_totalRecord(pDTO));
		pDTO.setR_totalRecord(Rservice.r_totalRecord(pDTO));
		pDTO.setB_totalRecord(Bservice.b_totalRecord(pDTO));
		pDTO.setU_totalRecord(Uservice.u_totalRecord(pDTO));
		
		List<MissionDTO> list = Mservice.Missionlist(pDTO);
		ModelAndView mav = new ModelAndView();
		
		// 검색어가 있으면 추출
		if (searchWord != null && !searchWord.isEmpty()) {
	        // 검색어 출력
			System.out.println(searchWord);

			// FastAPI 서비스의 URL을 정의합니다.
	        String fastApiUrl = "http://localhost:8000/dsearch_recommand"; 

	        // FastAPI로 전송할 데이터를 생성합니다.
	        Map<Object, Object> requestData = new HashMap<Object, Object>();
	        List<String> searchList = new ArrayList<>();
	        searchList.add(searchWord);
	        requestData.put("search", searchList);
	        
	        // HTTP 요청을 보내기 위한 RestTemplate을 만듭니다.
	        RestTemplate restTemplate = new RestTemplate();

	        // HTTP 헤더 설정
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        // HTTP 요청 엔티티 생성
	        HttpEntity<Map<Object,Object>> entity = new HttpEntity<Map<Object, Object>>(requestData, headers);

	        // FastAPI에 HTTP POST 요청을 보냅니다.
	        ResponseEntity<String> searchResponse = restTemplate.exchange(
	            fastApiUrl,
	            HttpMethod.POST,
	            entity,
	            String.class
	        );
	        System.out.println("FastAPI Response: " + searchResponse.getBody());

	        // FastAPI에서의 응답을 처리합니다.
	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, Object> contentResponseBody = mapper.readValue(searchResponse.getBody(), new TypeReference<Map<String, Object>>() {});
	        mav.addObject("search", contentResponseBody);
		}
		
		mav.addObject("M_list", list);
		mav.addObject("pDTO", pDTO);
		
		mav.setViewName("main/searchMission");
		return mav;
		
	}
	
	@GetMapping("/searchMaster")
	public ModelAndView searchMaster(PagingDTO pDTO) {
		pDTO.setM_totalRecord(Mservice.m_totalRecord(pDTO));
		pDTO.setQ_totalRecord(Qservice.q_totalRecord(pDTO));
		pDTO.setR_totalRecord(Rservice.r_totalRecord(pDTO));
		pDTO.setB_totalRecord(Bservice.b_totalRecord(pDTO));
		pDTO.setU_totalRecord(Uservice.u_totalRecord(pDTO));
		
		List<UserDTO> list = Uservice.Userlist(pDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("U_list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("main/searchMaster");
		return mav;
	}

	@GetMapping("/communityView")
	public String communityView() {
		return "community/communityView";
	}
	
	@GetMapping("/myPage")
	public String myPage() {
		return "main/mypage";
	}
	
	@GetMapping("/subscribe")
	public String popup() {
		return "main/popup";
	}
	
	@GetMapping("/missionIng")
	public String missionGoPopup() {
		return "main/missionGoPopup";
	}
	
	@GetMapping("/missionView")
	public String missionView() {
		return "mission/missionView";
	}
	
	@GetMapping("/missionStep")
	public String missionStep() {
		return "Mission/missionStep";
	}
	
	@GetMapping("/missionMake")
	public String missionMake() {
		return "mission/missionMake";
	}
}
