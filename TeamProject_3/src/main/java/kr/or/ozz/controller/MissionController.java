package kr.or.ozz.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public ModelAndView MissionView(@RequestParam("no")int no, PagingDTO pDTO) throws JsonProcessingException{
		//占쏙옙회占쏙옙 占쏙옙占쏙옙
		service.hitCount(no);

		// 占쏙옙占쌘드선占쏙옙
		MissionDTO dto = service.getMission(no);
		List<StepDTO> Steplist = Sservice.Steplist(no, pDTO);
		List<QnaDTO> M_Qnalist = Qservice.M_Qnalist(no);
		List<ReviewDTO> M_Reviewlist = Rservice.M_Reviewlist(no);
		
		// FastAPI 서비스의 URL을 정의합니다.
		List<String> contents = new ArrayList<String>();
		contents.add(dto.getMission_tag());

		// FastAPI 서비스의 URL을 정의합니다.
        String fastApiUrl = "http://localhost:8000/dmission_recommand"; // FastAPI �꽌踰� URL濡� �닔�젙

        // FastAPI로 전송할 데이터를 생성합니다.
        Map<Object, Object> requestData = new HashMap<Object, Object>();
        requestData.put("contents", contents);
        
        
     // HTTP 요청을 보내기 위한 RestTemplate을 만듭니다.
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청 엔티티 생성
        HttpEntity<Map<Object,Object>> entity = new HttpEntity<Map<Object, Object>>(requestData, headers);

        // FastAPI에 HTTP POST 요청을 보냅니다.
        ResponseEntity<String> contentResponse = restTemplate.exchange(
            fastApiUrl,
            HttpMethod.POST,
            entity,
            String.class
        );
        System.out.println("FastAPI Response: " + contentResponse.getBody());

        // FastAPI에서의 응답을 처리합니다.
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> contentResponseBody = mapper.readValue(contentResponse.getBody(), new TypeReference<Map<String, Object>>() {});
        List<Integer> missionNos = (List<Integer>) contentResponseBody.get("MISSION_NO");
        
        List<MissionDTO> missions = new ArrayList<>();
        for (Integer missionNO : missionNos) {
        	MissionDTO mdto = service.getMission(missionNO);
        	
        	if (mdto != null) {
        		missions.add(mdto);
        	}
        }
        
        System.out.println("리스트출력" + contentResponseBody);
        System.out.println("hi" + missions);
        System.out.println(contents);
        System.out.println(contentResponseBody);
        
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("Steplist", Steplist);
		mav.addObject("M_Qnalist", M_Qnalist);
		mav.addObject("M_Reviewlist", M_Reviewlist);
		mav.addObject("pDTO", pDTO);

		mav.addObject("contents", contentResponseBody);
<<<<<<< HEAD

=======
		mav.addObject("missions", missions);
>>>>>>> 0bff3fbcb854cabd70cc27c0377d40c8dd19a993
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
	         @RequestParam("pdf") final MultipartFile pdf, final HttpServletRequest request, final HttpSession session) {
		   
		   String logId = (String)session.getAttribute("logId"); 
		      dto.setUserid(logId);
		      
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

	   @PostMapping(value = "steptaskWriteOk")
	   public ResponseEntity<String> steptaskWriteOk(@ModelAttribute MissionDTO missionDTO, HttpServletRequest request) {

	      int mission_no = missionDTO.getMission_no();
	      List<StepDTO> steps = missionDTO.getSteps();
	      // 스텝 인덱스 아니고 화면에 표시될 스텝 순서 번호 생성
	      int step_num = 1;
	      for (int i = 0; i < steps.size(); i++) {
	         StepDTO step = steps.get(i);

	         // 스텝 식별 번호지정
	         step.setStep(step_num);

	         // 종속되는 미션 번호 지정
	         step.setMission_no(mission_no);

	         // 스텝 저장함
	         try {
	            service.stepCreate(step);
	         } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
	         }
	         // 저장된 스텝의 번호 가져옴(가장 최근 생성된걸로 가져오는 로직)
	         int step_no = service.getstep_no();

	         // 다음 스텝 식별용 번호 1올려야 하므로 더함
	         step_num++;

	         // 태스크 식별용 번호 생성
	         int task_num = 1;
	         List<TaskDTO> tasks = step.getTasks();
	         for (int j = 0; j < tasks.size(); j++) {
	            TaskDTO task = tasks.get(j);

	            // 종속되는 스텝 번호 넣음
	            task.setStep_no(step_no);

	            // 태스크 식별 번호 넣음
	            task.setTask(task_num);

	            // 태스크 파일이랑 경로 지정
	            MultipartFile file = task.getTask_file_name();
	            String path = request.getSession().getServletContext().getRealPath("/upload");

	            // 파일이 업로드되지 않았을 때
	            if (file == null || file.isEmpty()) {
	               task.setFile_name(""); // 파일명을 null로 설정하거나 기본값으로 설정 (DB에 null 허용 시)
	            } else {
	               String orgFileName = file.getOriginalFilename();
	               String baseName = FilenameUtils.getBaseName(orgFileName);
	               String extension = FilenameUtils.getExtension(orgFileName);

	               File f = new File(path, orgFileName);
	               int counter = 1;

	               while (f.exists()) {
	                  String newFileName = baseName + " (" + counter + ")." + extension;
	                  f = new File(path, newFileName);
	                  counter++;
	               }

	               try {
	                  Path filePath = Paths.get(path, f.getName());
	                  Files.createDirectories(filePath.getParent());
	                  Files.write(filePath, file.getBytes());
	               } catch (IOException e) {
	                  e.printStackTrace();
	                  return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
	               }

	               task.setFile_name(f.getName());
	            }

	            try {
	               service.taskCreate(task);
	            } catch (Exception e) {
	               e.printStackTrace();
	               return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);

	            }

	            task_num++;
	         }

	      }
	      return new ResponseEntity<>("success", HttpStatus.OK);
	   }
}