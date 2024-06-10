package com.taekwondo.entity;

import com.taekwondo.enums.Level;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "student")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Student {
	@Id
	@GeneratedValue(generator = "student-generator")
	@GenericGenerator(name = "student-generator",
			parameters = @Parameter(name = "prefix", value = "HV"),
			strategy = "com.taekwondo.utils.IdGeneratorUtil")
    private String id;

	@Column
	private String name;

	@Column
	private String gender;
	
	@Column
	private int yob;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Level level;

	@Column(unique = true)
	private String phoneNumber;

	@Column
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;
}
