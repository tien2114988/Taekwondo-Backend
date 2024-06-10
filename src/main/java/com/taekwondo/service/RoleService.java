package com.taekwondo.service;

import com.taekwondo.dto.permission.PermissionDto;
import com.taekwondo.dto.role.CreateRoleDto;
import com.taekwondo.dto.role.GetRoleDto;
import com.taekwondo.dto.student.GetStudentDto;
import com.taekwondo.entity.Permission;
import com.taekwondo.entity.Role;
import com.taekwondo.entity.Student;
import com.taekwondo.iservice.IRoleService;
import com.taekwondo.repository.IPermissionRepository;
import com.taekwondo.repository.IRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepo;

    @Autowired
    private IPermissionRepository permissionRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GetRoleDto getRoleById(String id) {
        Role role = roleRepo.findById(id).get();
        return modelMapper.map(role, GetRoleDto.class);
    }

    @Override
    public List<GetRoleDto> getAllRoles() {
        List<Role> roles = this.roleRepo.findAll();
        return roles.stream()
                .map(role -> modelMapper.map(role, GetRoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetRoleDto createRole(CreateRoleDto createRoleDto) {
        List<Permission> permissions = permissionRepo.findAllById(createRoleDto.getPermissions());
        Role role = modelMapper.map(createRoleDto, Role.class);

        role.setPermissions(new HashSet<>(permissions));

        role = this.roleRepo.save(role);
        return modelMapper.map(role, GetRoleDto.class);
    }

    @Override
    public GetRoleDto updateRole(CreateRoleDto createRoleDto) {
        Role role = roleRepo.findById(createRoleDto.getId()).get();
        modelMapper.map(createRoleDto, role);
        role = this.roleRepo.save(role);
        return modelMapper.map(role, GetRoleDto.class);
    }

    @Override
    public void deleteRole(String id) {
        Role role = roleRepo.findById(id).orElse(null);
        this.roleRepo.delete(role);
    }
}
