package com.pharmacy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pharmacy.entity.Admin;
import com.pharmacy.entity.Customer;
import com.pharmacy.entity.CustomerLoginHistory;
import com.pharmacy.entity.Otp;
import com.pharmacy.repository.CustomerLoginHistoryRepository;
import com.pharmacy.security.CustomUsernamePasswordAuthentication;
import com.pharmacy.security.EmailSending;
import com.pharmacy.security.FetchUserDetailsServiceFactory;
import com.pharmacy.security.LoginResponse;
import com.pharmacy.security.OtpRequest;
import com.pharmacy.services.EntityFetchService;
import com.pharmacy.services.JwtUtil;
import com.pharmacy.services.OtpService;

import jakarta.servlet.http.HttpServletRequest;
	
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private FetchUserDetailsServiceFactory userDetailsServiceFactory;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailSending email;
    
    @Autowired
    private JwtUtil jwtUtil;
    Admin admin=null;
    Customer customer = null;
    @Autowired
    private EntityFetchService entityFetchService;
    

	@Autowired
	private CustomerLoginHistoryRepository customerLoginRepo;
    
    @PostMapping({"/{userType}/login","/login"})
    public String login(@RequestBody  Object request, @PathVariable(required=false) String userType) {
    	Map<String, Object> data = (Map<String, Object>) request;
    	Authentication authentication = authenticationManager.authenticate(
                new CustomUsernamePasswordAuthentication(
                (String)data.get("username"), null,userType));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      
      Otp otp = otpService.generateOtp((String)data.get("username"));
      System.out.println("The otp is "+otp);
      String emaill = null;
      String name = null;
    	  if ("admin".equals(userType)) {
    		  admin= (Admin)entityFetchService.getEntityByUsername(userType, (String)data.get("username"));
    		  emaill = admin.getEmail();
    		  name = admin.getUserName();
    	 } else if ("customer".equals(userType)) {
        	 
         }else {
         throw new IllegalArgumentException("Invalid entityType: " + userType);
         }
    	  Map<String, Object> model = new HashMap<>();
          model.put("name",(String)data.get("username"));
          model.put("email", (String)data.get("username"));
          model.put("subscription", "Premium");
          model.put("otp",otp.getOtpCode());
          model.put("bankName","Medicare Pharmacy");
      email.sendEmail((String)data.get("username"), "Your otp for login to Medicare Pharmacy is : "
    		  +otp.getOtpCode() +".Please do not share it with any one.","OTP Verification",model);
      return "OTP generated successfully: "+otp.getOtpCode();
    }
    @PostMapping("/{userType}/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest otpReq,@PathVariable String userType,HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new CustomUsernamePasswordAuthentication(otpReq.getUsername(), otpReq.getOtp(),userType));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsService userDetailsService=  userDetailsServiceFactory.fetchUserDetailsService(userType);
        UserDetails user =  userDetailsService.loadUserByUsername(otpReq.getUsername());
        String jwtToken = jwtUtil.generateToken(otpReq.getUsername(),user.getAuthorities());
        customer = (Customer)entityFetchService.getEntityByUsername(userType, otpReq.getUsername());
   	 // emaill = customer.getEmail();
   	  //name = customer.getUserName();
        String clientIp = getClientIp(request);
        String location = getLocationFromIp(clientIp);
        String deviceUsed = request.getHeader("User-Agent");
        Map<String,Object>data = new HashMap();
        data.put("ipAddress",clientIp);
        data.put("location",location);
        data.put("deviceUsed",deviceUsed);
		 CustomerLoginHistory login = new CustomerLoginHistory(customer,data);
		 customerLoginRepo.save(login);
        LoginResponse response = new LoginResponse(jwtToken,customer);
        return ResponseEntity.ok(response);
    }
    
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For"); // Handles proxies
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    private String getLocationFromIp(String ip) {
        String apiUrl = "http://ip-api.com/json/" + ip;
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);
            if (response != null && "success".equals(response.get("status"))) {
                return response.get("city") + ", " + response.get("country");
            }
        } catch (Exception e) {
            return "Unknown Location";
        }
        return "Unknown Location";
    }
    
    
}
