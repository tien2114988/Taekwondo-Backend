package com.taekwondo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
	
	@Column(unique=true)
	private String username;
	
	@Column
	private String password;

	@Column
	@ManyToMany
	private Set<Role> roles;
	
	@OneToOne(mappedBy = "user")
    private Student student;
}
