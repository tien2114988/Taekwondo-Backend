package com.taekwondo.iservice;

import com.taekwondo.dto.student.CreateStudentDto;
import com.taekwondo.dto.student.GetStudentDto;
import com.taekwondo.dto.student.UpdateStudentDto;

import java.util.List;

public interface IStudentService {
    public GetStudentDto getStudentById(String id);
    public List<GetStudentDto> getAllStudents();
    public GetStudentDto createStudent(CreateStudentDto studentDto);
    public GetStudentDto updateStudent(UpdateStudentDto studentDto);
    public void deleteStudent(String id);
    public List<GetStudentDto> levelUpStudents(String[] ids);
}
