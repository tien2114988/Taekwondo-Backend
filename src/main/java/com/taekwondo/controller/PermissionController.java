package com.taekwondo.controller;

import com.taekwondo.dto.ApiResponse;
import com.taekwondo.dto.permission.PermissionDto;
import com.taekwondo.dto.student.CreateStudentDto;
import com.taekwondo.dto.student.GetStudentDto;
import com.taekwondo.dto.student.UpdateStudentDto;
import com.taekwondo.entity.Permission;
import com.taekwondo.service.PermissionService;
import com.taekwondo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionDto>> findOne(@PathVariable("id") String id) {
        PermissionDto permissionDto = permissionService.getPermissionById(id);
        ApiResponse<PermissionDto> res = ApiResponse.<PermissionDto>builder().count(1)
                .items(permissionDto).build();
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionDto>>> findAll() {
        List<PermissionDto> permissionDtoList = permissionService.getAllPermissions();
        ApiResponse<List<PermissionDto>> res = ApiResponse.<List<PermissionDto>>builder().count(permissionDtoList.size())
                .items(permissionDtoList).build();
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionDto>> create(@RequestBody PermissionDto permissionDto) {
        permissionDto = permissionService.createPermission(permissionDto);
        ApiResponse<PermissionDto> res = ApiResponse.<PermissionDto>builder().count(1)
                .items(permissionDto).build();
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") String id) {
        permissionService.deletePermission(id);
        ApiResponse<?> res = ApiResponse.builder().build();
        return ResponseEntity.ok(res);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<PermissionDto>> update(@RequestBody PermissionDto permissionDto) {
        permissionDto = permissionService.updatePermission(permissionDto);
        ApiResponse<PermissionDto> res = ApiResponse.<PermissionDto>builder().count(1)
                .items(permissionDto).build();
        return ResponseEntity.ok(res);
    }
}
