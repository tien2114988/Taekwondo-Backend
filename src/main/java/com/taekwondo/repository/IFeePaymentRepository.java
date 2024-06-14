package com.taekwondo.repository;

import com.taekwondo.entity.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeePaymentRepository extends JpaRepository<FeePayment, String> {
}
