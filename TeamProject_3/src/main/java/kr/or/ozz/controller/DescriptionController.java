package kr.or.ozz.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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

   @Async
   public ResponseEntity<List<Map<String, Object>>> processRequest(@RequestParam("title") String title,
         @RequestParam("pdf") MultipartFile pdf, HttpServletRequest request) {

      try {
         String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
         System.out.println(uploadPath);
         Path path = Paths.get(uploadPath, pdf.getOriginalFilename());

         Files.createDirectories(path.getParent());

         try {
            Files.write(Paths.get(path.toString()), pdf.getBytes());
         } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
         }

         String fastApiUrl = "http://localhost:8000/generate_description";
         RestTemplate restTemplate = new RestTemplate();

         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);

         Map<String, String> body = new HashMap<>();
         body.put("title", title);
         body.put("pdf_path", path.toString());

         HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

         ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(fastApiUrl, HttpMethod.POST,
               requestEntity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
               });

         List<Map<String, Object>> convertedData = convertNewline(response.getBody());

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