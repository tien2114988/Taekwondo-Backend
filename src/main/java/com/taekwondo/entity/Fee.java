package com.taekwondo.entity;


import com.taekwondo.enums.MonthEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fee {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private MonthEnum month;

    @Column
    private int year;

    @OneToMany(mappedBy = "fee")
    Set<FeePayment> students;
}
