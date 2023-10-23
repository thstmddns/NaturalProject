package kr.or.smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class DescriptionController {

	@PostMapping("/api/generate_description")
	@ResponseBody
	public ResponseEntity<List<Map<String, Object>>> processRequest(@RequestParam("title") String title,
			@RequestParam("pdf") MultipartFile pdf, HttpServletRequest request) {

		try {
			// 1. PDF 파일 저장 경로 생성
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
			Path path = Paths.get(uploadPath, pdf.getOriginalFilename());
			Files.createDirectories(path.getParent());

			// 2. PDF 파일 저장
			pdf.transferTo(new File(path.toString()));

			// 3. FastAPI 호출
			String fastApiUrl = "http://localhost:8000/generate_description";
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, String> body = new HashMap<String, String>();
			body.put("title", title);
			body.put("pdf_path", path.toString());

			HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(body, headers);

			ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(fastApiUrl, HttpMethod.POST,
					requestEntity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
					});

			// 응답 데이터를 변환
			List<Map<String, Object>> convertedData = convertNewline(response.getBody());
			System.out.println(convertedData);
			
			return ResponseEntity.status(response.getStatusCode()).body(convertedData);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	private List<Map<String, Object>> convertNewline(List<Map<String, Object>> data) {
		for (Map<String, Object> step : data) {
			List<Map<String, Object>> tasks = (List<Map<String, Object>>) step.get("테스크");
			if (tasks != null) {
				for (Map<String, Object> task : tasks) {
					if (task.containsKey("task_code")) {
						Object taskCode = task.get("task_code");
						task.put("task_code", convertNewlineString((String) taskCode)); // 이 부분에서 예외 발생
					}
				}
			}
		}
		return data;
	}

	private String convertNewlineString(String input) {
		if (input != null) {
			return input.replace("\\n", "\n");
		}
		return null; // 또는 다른 처리 방법에 따라 반환값을 설정하세요.
	}

}
//
//    private List<Map<String, Object>> convertNewline(List<Map<String, Object>> data) {
//        for (Map<String, Object> item : data) {
//            if (item.containsKey("예시 코드")) {
//                Object exampleCode = item.get("예시 코드");
//                if (exampleCode instanceof String) {
//                    item.put("예시 코드", convertNewlineString((String) exampleCode));
//                }
//            }
//        }
//        return data;
//    }

//	        
//	
//   // 파일 업로드 경로를 application.properties에서 가져옵니다.
//    private String uploadPath;
//
//    @PostMapping("/api/generate_description")
//    public ResponseEntity<String> processRequest(
//            @RequestParam("title") String title,
//            @RequestParam("pdf") MultipartFile pdf) {
//        try {
//            // PDF 파일을 서버에 저장
//            String savedFilePath = saveFile(pdf);
//
//            // FastAPI 호출
//            RestTemplate restTemplate = new RestTemplate();
//            String fastApiUrl = "http://localhost:8000/generate_description";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//            MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//            body.add("title", title);
//            body.add("pdf_url", savedFilePath); // 파일 경로를 전달합니다.
//
//            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(body, headers);
//            ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl, requestEntity, String.class);
//
//            return ResponseEntity.status(response.getStatusCode()).body(convertNewline(response.getBody()));
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File processing error: " + e.getMessage());
//        }
//    }
//
//    private String saveFile(MultipartFile file) throws IOException {
//        // 파일 저장 로직 (파일 이름 중복 방지 등이 필요할 수 있습니다)
//        String filePath = uploadPath + file.getOriginalFilename();
//        Files.write(Paths.get(filePath), file.getBytes());
//        return filePath;
//    }
//
//    private String convertNewline(String input) {
//        return input.replace("\\n", "\n");
//    }
//
//    @GetMapping("/upload")
//    public String uploadPage() {
//        return "upload";
//    }
//}