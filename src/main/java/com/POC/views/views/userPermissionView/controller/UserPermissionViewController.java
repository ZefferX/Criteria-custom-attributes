package com.POC.views.views.userPermissionView.controller;

import com.POC.views.views.criteria.Specification.GenericSpecification;
import com.POC.views.views.criteria.dto.GenericFilterDTO;
import com.POC.views.views.userPermissionView.dto.UserPermissionFilterDTO;
import com.POC.views.views.userPermissionView.model.UserPermissionView;
import com.POC.views.views.userPermissionView.service.UserPermissionViewService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.POC.views.views.shared.FilterUtils;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/generic-filter")
    public List<UserPermissionView> genericFilter(@RequestParam Map<String, String> params) {
        // Usar el método de utilidades
        GenericFilterDTO filterDTO = FilterUtils.parseParamsToDTO(params);
        Specification<UserPermissionView> spec = GenericSpecification.getSpecification(filterDTO);
        return service.findByGenericFilter(spec);
    }

}
