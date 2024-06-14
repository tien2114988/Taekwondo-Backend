package com.taekwondo.iservice;

import com.taekwondo.dto.permission.PermissionDto;
import com.taekwondo.dto.role.CreateRoleDto;
import com.taekwondo.dto.role.GetRoleDto;

import java.util.List;

public interface IRoleService {
    public GetRoleDto getRoleById(String id);
    public List<GetRoleDto> getAllRoles();
    public GetRoleDto createRole(CreateRoleDto createRoleDto);
    public GetRoleDto updateRole(CreateRoleDto createRoleDto);
    public void deleteRole(String id);
    public List<GetRoleDto> getRolesById(List<String> ids);
}
