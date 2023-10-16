package kr.or.ozz.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ozz.dto.ReplyDTO;

import kr.or.ozz.dto.MissionDTO;
import kr.or.ozz.dto.PagingDTO;
import kr.or.ozz.dto.QnaDTO;
import kr.or.ozz.dto.ReviewDTO;
import kr.or.ozz.dto.StepDTO;
import kr.or.ozz.dto.TaskDTO;
import kr.or.ozz.service.MissionService;

import kr.or.ozz.service.QnaService;
import kr.or.ozz.service.ReviewService;
import kr.or.ozz.service.StepService;
import kr.or.ozz.service.TaskService;

// @Controller : 占쏙옙, 占썰를 占쏙옙占쏙옙占쏙옙占쌔댐옙.
//				 ModelAndView,
//				 Model, String

// @RestController : 占쏙옙占쏙옙 占쏙옙占싹된댐옙.
//					 Model+viewPage -> ModelAndView占쏙옙 占쏙옙占쏙옙
@RestController
@RequestMapping("/Mission")
public class MissionController {
	@Autowired
	MissionService service;
	
	@Autowired
	StepService Sservice;
	
	@Autowired
	TaskService Tservice;
	
	@Autowired
	QnaService Qservice;
	
	@Autowired
	ReviewService Rservice;
	
	

	@GetMapping("/Missionlist")
	public ModelAndView Missionlist(PagingDTO pDTO) {
		// 占싼뤄옙占쌘듸옙占�
		pDTO.setM_totalRecord(service.m_totalRecord(pDTO));

		// 占쌔댐옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쌘듸옙 占쏙옙占쏙옙
		List<MissionDTO> list = service.Missionlist(pDTO);

		// ModelAndView
		ModelAndView mav = new ModelAndView();

		/*
		 * for (MissionDTO dto : list) { byte[] imageData = dto.getFile_name(); String
		 * base64ImageData = Base64.getEncoder().encodeToString(imageData); String
		 * base64ImageData; if (imageData != null) { base64ImageData =
		 * Base64.getEncoder().encodeToString(imageData);
		 * 
		 * } else { base64ImageData = "None"; }
		 * 
		 * dto.setFile_name_base64(base64ImageData); }
		 */
		mav.addObject("list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("Mission/Missionlist");
		return mav;
	}

	// 占쌜억옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싱듸옙
	@GetMapping("/Missionwrite")
	public ModelAndView Missionwrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Mission/missionMake");
		return mav;
	}

	// 占쌜억옙占쏙옙 DB 占쏙옙占�
	@PostMapping("/MissionwriteOk")
	public ResponseEntity<String> MissionwriteOk(MissionDTO dto, HttpServletRequest request) {
//		@RequestParam("file_name_base64") String base64ImageData
//		byte[] imageData;
//		if (base64ImageData == "") {
//			imageData = new byte[0];
//		} else {
//			imageData = Base64.getDecoder().decode(base64ImageData.split(",")[1]);
//		}
//
//		dto.setFile_name(imageData);
		// HttpServletRequest -> request, HttpSession
		// HttpSession -> session

		// no, hit, writedate -> 占쏙옙占쏙옙클
		// userid -> 占쏙옙占쏙옙

		// HttpSession session = request.getSession();
		// String userid = (String)session.getAttribute("logId");
		// dto.setUserid(userid);
		// 占쏙옙占쏙옙 占쏙옙치占쏙옙 占싣뤄옙 占쌘듸옙占� 占쏙옙占쏙옙
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		int result = 0;
		try {
			result = service.MissionwriteOk(dto);
		} catch (Exception e) {
			System.out.println("占쌉쏙옙占쏙옙 占쏙옙 占쏙옙占� 占쏙옙占쌤발삼옙..." + e.getMessage());
		}
		// 占쏙옙構占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙크占쏙옙트 占쏙옙占쏙옙占싹깍옙
		String tag = "<script>";
		if (result > 0) { // 占쏙옙占쏙옙 -> 占쌉쏙옙占쏙옙 占쏙옙占�
			tag += "location.href='/ozz/main/mainMission';";
		} else { // 占쏙옙占쏙옙 -> 占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙 占싱듸옙
			tag += "alert('占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占싹울옙占쏙옙占싹댐옙.');";
			tag += "history.back();";
		}
		tag += "</script>";

		// ResponseEntity 占쏙옙체占쏙옙 占쏙옙占쏙옙트占쏙옙占쏙옙占쏙옙占쏙옙 占쌜쇽옙占쏙옙 占쏙옙 占쌍댐옙.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(tag, headers, HttpStatus.OK);
	}

	// 占쌜놂옙占쎈보占쏙옙
	@GetMapping("/MissionView")
	public ModelAndView MissionView(@RequestParam("no")int no, PagingDTO pDTO, HttpSession session) {
		//占쏙옙회占쏙옙 占쏙옙占쏙옙
		service.hitCount(no);
		
	       
		// 占쏙옙占쌘드선占쏙옙
		MissionDTO dto = service.getMission(no);
		List<StepDTO> Steplist = Sservice.Steplist(no, pDTO);
		List<QnaDTO> M_Qnalist = Qservice.M_Qnalist(no);
		List<ReviewDTO> M_Reviewlist = Rservice.M_Reviewlist(no);
		
		// FastAPI�뿉 �쟾�넚�븷 �뜲�씠�꽣瑜� �깮�꽦
		List<String> contents = new ArrayList<String>();
		contents.add(dto.getMission_cate());

        // FastAPI �뿏�뱶�룷�씤�듃 URL
        String fastApiUrl = "http://localhost:8000/dmission_recommand"; // FastAPI �꽌踰� URL濡� �닔�젙

        // FastAPI�뿉 HTTP POST �슂泥��쓣 蹂대깄�땲�떎.
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("contents", contents);
        
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(requestBody, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl, requestEntity, String.class);

        // FastAPI�뿉�꽌 諛쏆� 寃곌낵 �뜲�씠�꽣瑜� �궗�슜�븯�뿬 ModelAndView �깮�꽦
        String resultData = response.getBody();
        
        System.out.println(contents);
        System.out.println(resultData);
//		List<> contents = dto.getMission_content();
//	    byte[] imageData = dto.getFile_name();
//        String base64ImageData = Base64.getEncoder().encodeToString(imageData);
//        dto.setFile_name_base64(base64ImageData);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("Steplist", Steplist);
		mav.addObject("M_Qnalist", M_Qnalist);
		mav.addObject("M_Reviewlist", M_Reviewlist);
		mav.addObject("pDTO", pDTO);
		mav.addObject("contents", resultData);
		
		mav.setViewName("Mission/missionView");

		return mav;
	}

	// 占쏙옙 占쏙옙占쏙옙 占쏙옙
	@GetMapping("/MissionEdit")
	public ModelAndView MissionEdit(int no) {
//		MissionDTO dto = service.getMission(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.getMission(no)); // dto 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쌉뤄옙
		mav.setViewName("Mission/MissionEdit");

		return mav;
	}

	@PostMapping("/MissionEditOk") // no, subject, content
	public ModelAndView MissionEditOk(MissionDTO dto, HttpSession session) {
		dto.setUserid((String) session.getAttribute("logId"));

		int result = service.MissionEdit(dto);

		ModelAndView mav = new ModelAndView();
		mav.addObject("no", dto.getMission_no());
		if (result > 0) { // 占쌜쇽옙占쏙옙占쏙옙占쏙옙 -> 占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
			mav.setViewName("redirect:MissionView");
		} else { // 占쌜쇽옙占쏙옙占쏙옙占쏙옙 -> 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙
			mav.setViewName("redirect:MissionEdit");
		}
		return mav;
	}

	// 占쌜삼옙占쏙옙
	@GetMapping("/MissionDel")
	public ModelAndView MissionDel(int no, HttpSession session) {
		int result = service.MissionDel(no, (String) session.getAttribute("logId"));

		ModelAndView mav = new ModelAndView();
		if (result > 0) {// 占쏙옙占쏙옙占쏙옙占쏙옙 -> 占쏙옙占�
			mav.setViewName("redirect:Missionlist");
		} else {// 占쏙옙占쏙옙占쏙옙占쏙옙 -> 占쌜놂옙占쏙옙
			mav.addObject("no", no);
			mav.setViewName("redirect:MissionView");
		}
		return mav;
	}
	
	/*
	 * // 占쏙옙占쏙옙 占쏙옙占�
	 * 
	 * @PostMapping("/review/reviewWrite") public String reviewWrite(ReplyDTO dto,
	 * HttpSession session) { // session 占쌜억옙占쏙옙 占쏙옙占싹깍옙
	 * dto.setUserid((String)session.getAttribute("logId"));
	 * 
	 * int result = service.replyInsert(dto);
	 * 
	 * return result+""; }
	 */
	
	
}