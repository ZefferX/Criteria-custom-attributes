package com.POC.views.views.criteria.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GenericFilterDTO(List<Filter> filters,
                               String orderBy,
                               String order
){
    @Builder
    public record Filter(
            String field,       // Campo sobre el que se aplica el filtro
            String operator,    // Operador: eq, like, gt, lt, etc.
            String value        // Valor a comparar
    ) {}
}
