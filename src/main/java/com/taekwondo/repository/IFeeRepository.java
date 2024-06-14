package com.taekwondo.repository;

import com.taekwondo.entity.Fee;
import com.taekwondo.enums.MonthEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFeeRepository extends JpaRepository<Fee, String> {
    boolean existsByMonthAndYear(MonthEnum month, int year);
}
