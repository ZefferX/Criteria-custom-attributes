package com.POC.views.views.userPermissionView.controller;

import com.POC.views.views.userPermissionView.dto.UserPermissionFilterDTO;
import com.POC.views.views.userPermissionView.model.UserPermissionView;
import com.POC.views.views.userPermissionView.service.UserPermissionViewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-permission")
@AllArgsConstructor
public class UserPermissionViewController {
    private final UserPermissionViewService service;

    // Endpoint para obtener todos los registros
    @GetMapping
    public List<UserPermissionView> findAll() {
        return service.findAll();
    }

    // Endpoint para obtener los usuarios asociados a un permiso específico
    @GetMapping("/permission/{permissionId}")
    public ResponseEntity<List<UserPermissionView>> findByPermissionId(@PathVariable Long permissionId) {
        List<UserPermissionView> results = service.findByPermissionId(permissionId);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/filter")
    public List<UserPermissionView> filterPermissions(UserPermissionFilterDTO filterDTO) {
        return service.findFiltered(filterDTO);
    }

}
