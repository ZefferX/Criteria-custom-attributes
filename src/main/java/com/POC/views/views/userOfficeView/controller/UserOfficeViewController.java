package com.POC.views.views.userOfficeView.controller;

import com.POC.views.views.criteria.Specification.GenericSpecification;
import com.POC.views.views.criteria.dto.GenericFilterDTO;
import com.POC.views.views.shared.FilterUtils;
import com.POC.views.views.userOfficeView.model.UserOfficeView;
import com.POC.views.views.userOfficeView.service.UserOfficeViewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user-office")
@AllArgsConstructor
public class UserOfficeViewController {

    private final UserOfficeViewService service;

    // Endpoint para obtener todos los registros
    @GetMapping
    public List<UserOfficeView> findAll() {
        return service.findAll();
    }

    @GetMapping("/generic-filter")
    public Page<UserOfficeView> genericFilter(
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page, // Página inicial (por defecto: 0)
            @RequestParam(defaultValue = "10") int size, // Tamaño de la página (por defecto: 10)
            @RequestParam(defaultValue = "USER_ID,asc") String sort // Orden (por defecto: USER_ID ascendente)
    ) {
        GenericFilterDTO filterDTO = FilterUtils.parseParamsToDTO(params);
        Specification<UserOfficeView> spec = GenericSpecification.getSpecification(filterDTO);

        // Construir el objeto Pageable
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.by(sort.split(",")[0])
                .with(Sort.Direction.fromString(sort.split(",")[1]))));

        return service.findByGenericFilter(spec, pageable);
    }

}
