package com.taekwondo.repository;

import com.taekwondo.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<Permission, String> {
}
