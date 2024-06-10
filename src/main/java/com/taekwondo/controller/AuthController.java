package com.taekwondo.controller;

import com.nimbusds.jose.JOSEException;
import com.taekwondo.dto.ApiResponse;
import com.taekwondo.dto.auth.CreateLoginDto;
import com.taekwondo.dto.auth.GetLoginDto;
import com.taekwondo.dto.auth.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.taekwondo.service.AuthService;

import java.text.ParseException;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @GetMapping
    public String test(){return secretKey;}

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<GetLoginDto>> login(@RequestBody CreateLoginDto createLoginDto) {
        GetLoginDto getLoginDto = authService.login(createLoginDto);
        ApiResponse<GetLoginDto> res = ApiResponse.<GetLoginDto>builder().items(getLoginDto).build();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<TokenDto>> logout(@RequestBody TokenDto tokenDto) throws ParseException, JOSEException {
        authService.logout(tokenDto.getToken());
        ApiResponse<TokenDto> res = ApiResponse.<TokenDto>builder().build();
        return ResponseEntity.ok(res);
    }

//    @PostMapping("/verify")
//    public ResponseEntity<ApiResponse<?>> verify(@RequestBody TokenDto tokenDto) {
//        authService.verify(tokenDto.getToken());
//        ApiResponse<?> res = ApiResponse.<TokenDto>builder().build();
//        return ResponseEntity.ok(res);
//    }
}
