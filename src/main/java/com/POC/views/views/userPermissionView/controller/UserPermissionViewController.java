package com.POC.views.views.userPermissionView.controller;


import com.POC.views.views.criteria.Specification.GenericSpecification;
import com.POC.views.views.criteria.dto.GenericFilterDTO;
import com.POC.views.views.shared.FilterUtils;
import com.POC.views.views.userPermissionView.model.UserPermissionView;
import com.POC.views.views.userPermissionView.service.UserPermissionViewService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/generic-filter")
    public Page<UserPermissionView> genericFilter (
            @RequestParam Map<String, String> params,
            @RequestParam (defaultValue="0") int page,
            @RequestParam(defaultValue = "10") int size, // Tamaño de la página (por defecto: 10)
            @RequestParam(defaultValue = "USER_ID,asc") String sort
    )
    {
        GenericFilterDTO filterDTO = FilterUtils.parseParamsToDTO(params);
        Specification<UserPermissionView> spec = GenericSpecification.getSpecification(filterDTO);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.by(sort.split(",")[0])
                .with(Sort.Direction.fromString(sort.split(",")[1]))));

        return service.findByGenericFilter(spec, pageable);
    }

}
