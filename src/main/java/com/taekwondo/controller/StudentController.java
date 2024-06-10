package com.taekwondo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.taekwondo.dto.ApiResponse;
import com.taekwondo.dto.student.CreateStudentDto;
import com.taekwondo.dto.student.GetStudentDto;
import com.taekwondo.dto.student.UpdateStudentDto;
import com.taekwondo.dto.user.CreateUserDto;
import com.taekwondo.enums.StatusCode;
import com.taekwondo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.taekwondo.service.StudentService;

@RestController
@RequestMapping("api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<GetStudentDto>> findOne(@PathVariable("id") String id) {
		GetStudentDto studentDto = studentService.getStudentById(id);
		ApiResponse<GetStudentDto> res = ApiResponse.<GetStudentDto>builder().count(1)
				.items(studentDto).build();
		return ResponseEntity.ok(res);
	}
	
	@GetMapping
    public ResponseEntity<ApiResponse<List<GetStudentDto>>> findAll() {
		List<GetStudentDto> studentDtoList = studentService.getAllStudents();
		ApiResponse<List<GetStudentDto>> res = ApiResponse.<List<GetStudentDto>>builder().count(studentDtoList.size())
				.items(studentDtoList).build();
		return ResponseEntity.ok(res);
    }
	
	@PostMapping
	public ResponseEntity<ApiResponse<GetStudentDto>> create(@RequestBody CreateStudentDto createStudentDto) {
		GetStudentDto studentDto = studentService.createStudent(createStudentDto);

		ApiResponse<GetStudentDto> res = ApiResponse.<GetStudentDto>builder().count(1)
				.items(studentDto).build();
		return ResponseEntity.ok(res);
	}

	@PostMapping("/levelUp")
	public ResponseEntity<ApiResponse<List<GetStudentDto>>> levelUp(@RequestBody String[] ids) {
		List<GetStudentDto> getStudentDtoList = studentService.levelUpStudents(ids);
		ApiResponse<List<GetStudentDto>> res = ApiResponse.<List<GetStudentDto>>builder().items(getStudentDtoList).count(getStudentDtoList.size()).build();
		return ResponseEntity.ok(res);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") String id) {
		studentService.deleteStudent(id);
		ApiResponse<?> res = ApiResponse.builder().build();
		return ResponseEntity.ok(res);
	}

	@PutMapping
	public ResponseEntity<ApiResponse<GetStudentDto>> update(@RequestBody UpdateStudentDto updateStudentDto) {
		GetStudentDto studentDto = studentService.updateStudent(updateStudentDto);
		ApiResponse<GetStudentDto> res = ApiResponse.<GetStudentDto>builder().count(1)
				.items(studentDto).build();
		return ResponseEntity.ok(res);
	}
}
