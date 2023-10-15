package kr.or.smhrd.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.or.ozz.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/")
public class LandingRecommandController {
    
//    @GetMapping("/dlanding_recommand")
//    public List<String> dlandingRecommand(@RequestParam String concern) {
//        // You can implement the landing page recommendation logic here
//        // Call your existing 'landing_recommand' function and return the results
//        List<String> recommendations = landing_recommand(concern);
//        return recommendations;
//    }
    
    // Import your 'landing_recommand' function here, and update its return type accordingly
    private List<String> landing_recommand(String concern) {
        // Implement your recommendation logic here
        // You can call your existing 'landing_recommand' function or write new code
        // Update the return type and logic as needed
        // Return a list of recommended items
        return null;
    }
    @PostMapping("/main/landing")
    public List<String> dlandingRecommand(@RequestParam String concern){
    	System.out.println(1);
    	UserDTO dto = new UserDTO();
    	String uConcern = dto.getConcern();
    	RestTemplate restTemplate = new RestTemplate();
        String fastApiUrl = "http://127.0.0.1:8000/dlanding_recommand";
        ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl, concern, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
        	
            return (List<String>) new ResponseEntity<String>("Data sent to FastAPI successfully", HttpStatus.OK);
        } else {
            return (List<String>) new ResponseEntity<String>("Failed to send data to FastAPI", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}