package com.taekwondo.controller;

import com.taekwondo.dto.ApiResponse;
import com.taekwondo.dto.role.CreateRoleDto;
import com.taekwondo.dto.role.GetRoleDto;

import com.taekwondo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetRoleDto>> findOne(@PathVariable("id") String id) {
        GetRoleDto getRoleDto = roleService.getRoleById(id);
        ApiResponse<GetRoleDto> res = ApiResponse.<GetRoleDto>builder().count(1)
                .items(getRoleDto).build();
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetRoleDto>>> findAll() {
        List<GetRoleDto> roleDtoList = roleService.getAllRoles();
        ApiResponse<List<GetRoleDto>> res = ApiResponse.<List<GetRoleDto>>builder().count(roleDtoList.size())
                .items(roleDtoList).build();
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GetRoleDto>> create(@RequestBody CreateRoleDto roleDto) {
        GetRoleDto getRoleDto = roleService.createRole(roleDto);
        ApiResponse<GetRoleDto> res = ApiResponse.<GetRoleDto>builder().count(1)
                .items(getRoleDto).build();
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") String id) {
        roleService.deleteRole(id);
        ApiResponse<?> res = ApiResponse.builder().build();
        return ResponseEntity.ok(res);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<GetRoleDto>> update(@RequestBody CreateRoleDto roleDto) {
        GetRoleDto getRoleDto = roleService.updateRole(roleDto);
        ApiResponse<GetRoleDto> res = ApiResponse.<GetRoleDto>builder().count(1)
                .items(getRoleDto).build();
        return ResponseEntity.ok(res);
    }
}
