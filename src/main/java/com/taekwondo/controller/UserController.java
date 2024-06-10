//package com.taekwondo.controller;
//
//
//import com.taekwondo.dto.ApiResponse;
//import com.taekwondo.dto.user.GetUserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.taekwondo.dto.user.CreateUserDto;
//
//import com.taekwondo.service.UserService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("api/users")
//public class UserController {
//	@Autowired
//	private UserService userService;
//
//
//	@PostMapping
//	public ResponseEntity<ApiResponse<GetUserDto>> signUp(@RequestBody @Valid CreateUserDto userDto) {
//		GetUserDto getUserDto = this.userService.create(userDto);
//		ApiResponse<GetUserDto> res = ApiResponse.<GetUserDto>builder().items(getUserDto).count(1).build();
//		return ResponseEntity.ok(res);
//	}
//}
