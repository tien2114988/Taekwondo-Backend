package com.taekwondo.service;

import com.taekwondo.dto.fee.CreateFeeDto;
import com.taekwondo.dto.fee.GetFeeDto;
import com.taekwondo.entity.Fee;
import com.taekwondo.entity.FeePayment;
import com.taekwondo.iservice.IFeeService;
import com.taekwondo.iservice.IStudentService;
import com.taekwondo.repository.IFeePaymentRepository;
import com.taekwondo.repository.IFeeRepository;
import com.taekwondo.repository.IStudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taekwondo.enums.MonthEnum;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import java.util.List;

@Service
@Slf4j
public class FeeService implements IFeeService {
    @Autowired
    private IFeeRepository feeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IFeePaymentRepository feePaymentRepo;

    @Autowired
    private IStudentRepository studentRepo;

    @Override
    public void createFee(CreateFeeDto createFeeDto) {
        Fee fee = modelMapper.map(createFeeDto, Fee.class);
        feeRepo.save(fee);
    }

    @Override
    public void scheduleAddFee() {
        List<Fee> fees = new ArrayList<Fee>();
        List<FeePayment> feePayments = new ArrayList<FeePayment>();
        LocalDate currentDate = LocalDate.now();
        Month month = currentDate.getMonth();
        int year = currentDate.getYear();
        int count = 0;
        while(count < 10){
            if(!feeRepo.existsByMonthAndYear(MonthEnum.valueOf(month.toString()),year)){
                Fee fee = Fee.builder().month(MonthEnum.values()[month.getValue()-1]).year(year).build();
                studentRepo.findAll().forEach(student -> {
                    FeePayment feePayment = FeePayment.builder().student(student).fee(fee).build();
                    feePayments.add(feePayment);
                });

                fees.add(fee);
            }
            if(month == Month.DECEMBER){
                year++;
            }
            month = month.plus(1);
            count++;
        }

        feeRepo.saveAll(fees);
        feePaymentRepo.saveAll(feePayments);
    }

    @Override
    public void initFee() {
        List<Fee> fees = new ArrayList<Fee>();
        LocalDate currentDate = LocalDate.now();
        Month month = currentDate.getMonth();
        int year = currentDate.getYear();
        int count = 0;
        while(count < 10){
            if(month == Month.JANUARY){
                year--;
            }
            month = month.minus(1);

            if(!feeRepo.existsByMonthAndYear(MonthEnum.valueOf(month.toString()),year)){
                fees.add(Fee.builder().month(MonthEnum.values()[month.getValue()-1]).year(year).build());
            }
            count++;
        }

        feeRepo.saveAll(fees);
    }
}
