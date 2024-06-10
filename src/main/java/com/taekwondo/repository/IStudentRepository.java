package com.taekwondo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.taekwondo.entity.Student;

import java.util.Optional;

@Repository
public interface IStudentRepository extends JpaRepository<Student, String> {
    @NonNull
    public Optional<Student> findById(@NonNull String id);
}
