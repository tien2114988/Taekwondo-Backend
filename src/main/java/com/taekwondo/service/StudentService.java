package com.taekwondo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.taekwondo.dto.role.GetRoleDto;
import com.taekwondo.dto.student.CreateStudentDto;
import com.taekwondo.dto.student.GetStudentDto;
import com.taekwondo.dto.student.UpdateStudentDto;
import com.taekwondo.dto.user.CreateUserDto;
import com.taekwondo.entity.*;
import com.taekwondo.iservice.IStudentService;
import com.taekwondo.repository.IFeePaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taekwondo.repository.IStudentRepository;

@Service
public class StudentService implements IStudentService {
	@Autowired
	private IStudentRepository studentRepo;

	@Autowired
	private IFeePaymentRepository iFeePamentRepo;
	
	@Autowired 
	private ModelMapper modelMapper;

	@Autowired
	private RoleService roleService;

	@Autowired UserService userService;

	@Autowired FeeService feeService;

	public GetStudentDto getStudentById(String id) {
		Student student = studentRepo.findById(id).get();
		return modelMapper.map(student, GetStudentDto.class);
	}


//	@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
	public List<GetStudentDto> getAllStudents(){
		List<Student> students = this.studentRepo.findAll();
		return students.stream()
		          .map(student -> modelMapper.map(student, GetStudentDto.class))
		          .collect(Collectors.toList());
	}
	
	public GetStudentDto createStudent(CreateStudentDto createStudentDto){
		Student student = modelMapper.map(createStudentDto, Student.class);

		student = this.studentRepo.save(student);

		String username = student.getPhoneNumber();
		String password = student.getPhoneNumber();
		List<GetRoleDto> rolesDto = roleService.getRolesById(List.of("USER"));
		Set<Role> roles = rolesDto.stream().map(role -> modelMapper.map(role, Role.class)).collect(Collectors.toSet());

		User user = userService.create(new CreateUserDto(username, password, roles, student));

		student.setUser(user);

		List<FeePayment> fees = iFeePamentRepo.findAllById(createStudentDto.getFees());
		student.setFees(new HashSet<>(fees));

		student = this.studentRepo.save(student);

		return modelMapper.map(student, GetStudentDto.class);
	}

	public void deleteStudent(String id){
		Student student = studentRepo.findById(id).orElse(null);

		userService.delete(student.getUser().getId());

		this.studentRepo.delete(student);
	}

	@Override
	public List<GetStudentDto> levelUpStudents(String[] ids) {
		List<Student> students = studentRepo.findAllById(List.of(ids));
		students.stream().forEach(student -> student.setLevel(student.getLevel().next()));
		students = this.studentRepo.saveAll(students);
		return students.stream()
				.map(student -> modelMapper.map(student, GetStudentDto.class))
				.collect(Collectors.toList());
	}

	public GetStudentDto updateStudent(UpdateStudentDto updateStudentDto){
		Student student = studentRepo.findById(updateStudentDto.getId()).get();
		modelMapper.map(updateStudentDto, student);
		List<FeePayment> fees = iFeePamentRepo.findAllById(updateStudentDto.getFees());
		student.setFees(new HashSet<>(fees));
		student = this.studentRepo.save(student);
		return modelMapper.map(student, GetStudentDto.class);
	}
}
