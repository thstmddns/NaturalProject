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
			// 1. PDF ���� ���� ��� ����
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
			Path path = Paths.get(uploadPath, pdf.getOriginalFilename());
			Files.createDirectories(path.getParent());

			// 2. PDF ���� ����
			pdf.transferTo(new File(path.toString()));

			// 3. FastAPI ȣ��
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

			// ���� �����͸� ��ȯ
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
			List<Map<String, Object>> tasks = (List<Map<String, Object>>) step.get("�׽�ũ");
			if (tasks != null) {
				for (Map<String, Object> task : tasks) {
					if (task.containsKey("task_code")) {
						Object taskCode = task.get("task_code");
						task.put("task_code", convertNewlineString((String) taskCode)); // �� �κп��� ���� �߻�
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
		return null; // �Ǵ� �ٸ� ó�� ����� ���� ��ȯ���� �����ϼ���.
	}

}
//
//    private List<Map<String, Object>> convertNewline(List<Map<String, Object>> data) {
//        for (Map<String, Object> item : data) {
//            if (item.containsKey("���� �ڵ�")) {
//                Object exampleCode = item.get("���� �ڵ�");
//                if (exampleCode instanceof String) {
//                    item.put("���� �ڵ�", convertNewlineString((String) exampleCode));
//                }
//            }
//        }
//        return data;
//    }

//	        
//	
//   // ���� ���ε� ��θ� application.properties���� �����ɴϴ�.
//    private String uploadPath;
//
//    @PostMapping("/api/generate_description")
//    public ResponseEntity<String> processRequest(
//            @RequestParam("title") String title,
//            @RequestParam("pdf") MultipartFile pdf) {
//        try {
//            // PDF ������ ������ ����
//            String savedFilePath = saveFile(pdf);
//
//            // FastAPI ȣ��
//            RestTemplate restTemplate = new RestTemplate();
//            String fastApiUrl = "http://localhost:8000/generate_description";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//            MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//            body.add("title", title);
//            body.add("pdf_url", savedFilePath); // ���� ��θ� �����մϴ�.
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
//        // ���� ���� ���� (���� �̸� �ߺ� ���� ���� �ʿ��� �� �ֽ��ϴ�)
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