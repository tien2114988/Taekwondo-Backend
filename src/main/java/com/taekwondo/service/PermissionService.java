package com.taekwondo.service;

import com.taekwondo.dto.permission.PermissionDto;
import com.taekwondo.dto.student.GetStudentDto;
import com.taekwondo.entity.Permission;
import com.taekwondo.entity.Student;
import com.taekwondo.iservice.IPermissionService;
import com.taekwondo.repository.IPermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionRepository permissionRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionDto getPermissionById(String id) {
        Permission permission = permissionRepo.findById(id).get();
        return modelMapper.map(permission, PermissionDto.class);
    }

    @Override
    public List<PermissionDto> getAllPermissions() {
        List<Permission> permissions = this.permissionRepo.findAll();
        return permissions.stream()
                .map(permission -> modelMapper.map(permission, PermissionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PermissionDto createPermission(PermissionDto permissionDto) {
        Permission permission = modelMapper.map(permissionDto, Permission.class);
        permission = this.permissionRepo.save(permission);
        return modelMapper.map(permission, PermissionDto.class);
    }

    @Override
    public PermissionDto updatePermission(PermissionDto permissionDto) {
        Permission permission = permissionRepo.findById(permissionDto.getId()).get();
        modelMapper.map(permissionDto, permission);
        permission = this.permissionRepo.save(permission);
        return modelMapper.map(permission, PermissionDto.class);
    }

    @Override
    public void deletePermission(String id) {
        Permission permission = permissionRepo.findById(id).orElse(null);
        this.permissionRepo.delete(permission);
    }
}
