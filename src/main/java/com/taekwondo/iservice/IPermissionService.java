package com.taekwondo.iservice;

import com.taekwondo.dto.permission.PermissionDto;

import java.util.List;

public interface IPermissionService {
    public PermissionDto getPermissionById(String id);
    public List<PermissionDto> getAllPermissions();
    public PermissionDto createPermission(PermissionDto permissionDto);
    public PermissionDto updatePermission(PermissionDto permissionDto);
    public void deletePermission(String id);
}
