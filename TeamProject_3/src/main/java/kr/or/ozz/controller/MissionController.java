package kr.or.ozz.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
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
	

	@Autowired
	private DescriptionController descriptionController;


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
	public ModelAndView MissionView(@RequestParam("no")int no, PagingDTO pDTO) {
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
	

	// 스텝 테스크 작성(수동)
	   @PostMapping("/selfGenerate")
	   public ModelAndView steptaskWrite(MissionDTO dto, HttpSession session) {

	      
	      String logId = (String)session.getAttribute("logId"); 
	      dto.setUserid(logId);
	      
	      try {
	         // 미션 생성
	         service.missionCreate(dto);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      // 가장 최근 생성된 미션 번호 가져오는 로직
	      int mission_no = service.getmission_no();
	      ModelAndView mav = new ModelAndView();
	      mav.addObject("mission_no", mission_no);
	      mav.setViewName("Mission/stepMake");
	      return mav;
	   }
	   
	// 스텝 태스크 작성(자동)
	   @PostMapping("/aiGenerate")
	   @ResponseBody
	   public String autoGeneration(final MissionDTO dto, @RequestParam("title") final String title,
	         @RequestParam("pdf") final MultipartFile pdf, final HttpServletRequest request) {

	      try {
	         // 아래는 desciptioncontroller로 보내는 코드
	         CompletableFuture.runAsync(new Runnable() {
	            @Override
	            public void run() {
	               ResponseEntity<List<Map<String, Object>>> responseEntity = descriptionController
	                     .processRequest(title, pdf, request);

	               // 파이썬으로부터 생성된 json 데이터 받아옴
	               List<Map<String, Object>> result = responseEntity.getBody();
	               try {
	                  // 미션 번호가 필요하기 때문에 미션 먼저 생성함
	                  service.missionCreate(dto); // 미션을 생성합니다.
	               } catch (Exception e) {
	                  e.printStackTrace();
	               }
	               // 미션 번호 가져옴
	               int mission_no = service.getmission_no();

	               // 스텝 인덱스 아니고 화면에 표시될 스텝 순서 번호 생성
	               int step = 1;

	               // 스텝 하나씩 저장함
	               for (Map<String, Object> stepMap : result) {
	                  StepDTO sdto = new StepDTO();
	                  // 파이썬 받아온 json으로 부터 스텝 이름 가져와서 step객체에 넣음
	                  String stepName = (String) stepMap.get("단계");
	                  sdto.setStep_title(stepName);

	                  // 위에서 받아온 미션 번호 넣음
	                  sdto.setMission_no(mission_no);

	                  // 스텝 식별 번호 넣음
	                  sdto.setStep(step);

	                  // 스텝 저장함
	                  try {
	                	  service.stepCreate(sdto);
	                  } catch (Exception e) {
	                     e.printStackTrace();
	                  }
	                  // 저장된 스텝의 번호 가져옴(가장 최근 생성된걸로 가져오는 로직)
	                  int step_no = service.getstep_no();

	                  // 다음 스텝 식별용 번호 1올려야 하므로 더함
	                  step++;

	                  // 태스크 식별용 번호 생성
	                  int task = 1;

	                  // 스텝 객체안의 태스크 객체 반복 생성
	                  List<Map<String, Object>> tasks = (List<Map<String, Object>>) stepMap.get("태스크");
	                  for (Map<String, Object> taskMap : tasks) {
	                     TaskDTO tdto = new TaskDTO();
	                     // 파이썬의 json코드에는 태스크 이름과 코드가져와서 넣음
	                     String taskName = (String) taskMap.get("task_name");
	                     String taskCode = (String) taskMap.get("task_code");
	                     tdto.setTask_title(taskName);
	                     tdto.setTask_content(taskCode);

	                     // 종속되는 스텝 인덱스 넣음
	                     tdto.setStep_no(step_no);

	                     // 태스크 식별 번호 넣음
	                     tdto.setTask(task);
	                     // 파일이름이 없으면 에러나기때문에 넣음. 확인해보니 이렇게 하면 db에 null로 들어감
	                     tdto.setFile_name("");
	                     // 태스크 생성함
	                     try {
	                        service.taskCreate(tdto);
	                     } catch (Exception e) {
	                        e.printStackTrace();
	                     }

	                     // 태스크 식별용 번호 1 더함
	                     task++;
	                  }
	               }
	            }
	         });
	         // 여기까지 문제없으면 성공
	         return "success";
	      }

	      // 실패할 경우
	      catch (Exception e) {
	         e.printStackTrace();
	         return "failure";
	      }
	   }  

	
}