package com.taekwondo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.taekwondo.dto.student.CreateStudentDto;
import com.taekwondo.dto.student.GetStudentDto;
import com.taekwondo.dto.student.UpdateStudentDto;
import com.taekwondo.dto.user.CreateUserDto;
import com.taekwondo.entity.Role;
import com.taekwondo.entity.User;
import com.taekwondo.exception.AppException;
import com.taekwondo.iservice.IStudentService;
import com.taekwondo.repository.IRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.taekwondo.entity.Student;
import com.taekwondo.repository.IStudentRepository;

@Service
public class StudentService implements IStudentService {
	@Autowired
	private IStudentRepository iStudentRepo;

	@Autowired
	private IRoleRepository iRoleRepo;
	
	@Autowired 
	private ModelMapper modelMapper;

	@Autowired UserService userService;

	public GetStudentDto getStudentById(String id) {
		Student student = iStudentRepo.findById(id).get();
		return modelMapper.map(student, GetStudentDto.class);
	}


//	@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
	public List<GetStudentDto> getAllStudents(){
		List<Student> students = this.iStudentRepo.findAll();
		return students.stream()
		          .map(student -> modelMapper.map(student, GetStudentDto.class))
		          .collect(Collectors.toList());
	}
	
	public GetStudentDto createStudent(CreateStudentDto createStudentDto){
		Student student = modelMapper.map(createStudentDto, Student.class);

		student = this.iStudentRepo.save(student);

		String username = student.getPhoneNumber();
		String password = student.getPhoneNumber();
		List<Role> roles = iRoleRepo.findAllById(List.of("USER"));

		User user = userService.create(new CreateUserDto(username, password, new HashSet<>(roles), student));

		student.setUser(user);

		student = this.iStudentRepo.save(student);

		return modelMapper.map(student, GetStudentDto.class);
	}

	public void deleteStudent(String id){
		Student student = iStudentRepo.findById(id).orElse(null);

		userService.delete(student.getUser().getId());

		this.iStudentRepo.delete(student);
	}

	@Override
	public List<GetStudentDto> levelUpStudents(String[] ids) {
		List<Student> students = iStudentRepo.findAllById(List.of(ids));
		students.stream().forEach(student -> student.setLevel(student.getLevel().next()));
		students = this.iStudentRepo.saveAll(students);
		return students.stream()
				.map(student -> modelMapper.map(student, GetStudentDto.class))
				.collect(Collectors.toList());
	}

	public GetStudentDto updateStudent(UpdateStudentDto updateStudentDto){
		Student student = iStudentRepo.findById(updateStudentDto.getId()).get();
		modelMapper.map(updateStudentDto, student);
		student = this.iStudentRepo.save(student);
		return modelMapper.map(student, GetStudentDto.class);
	}
}
