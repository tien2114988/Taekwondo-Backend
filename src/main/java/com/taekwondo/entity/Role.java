package com.taekwondo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String id;

    @Column
    private String description;

    @Column
    @ManyToMany(fetch= FetchType.EAGER)
    private Set<Permission> permissions;
}
