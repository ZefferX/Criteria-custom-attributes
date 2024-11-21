package com.POC.views.views.shared;

import com.POC.views.views.criteria.dto.GenericFilterDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterUtils {
    /**
     * Convierte los parámetros de una URL en un GenericFilterDTO.
     *
     * @param params Los parámetros de la URL como un Map.
     * @return Un objeto GenericFilterDTO construido a partir de los parámetros.
     */
    public static GenericFilterDTO parseParamsToDTO(Map<String, String> params) {
        List<GenericFilterDTO.Filter> filters = new ArrayList<>();

        // Procesar los filtros de la URL
        for (int i = 0; params.containsKey("filters[" + i + "][field]"); i++) {
            String field = params.get("filters[" + i + "][field]");
            String operator = params.get("filters[" + i + "][operator]");
            String value = params.get("filters[" + i + "][value]");

            if (field != null && operator != null && value != null) {
                filters.add(GenericFilterDTO.Filter.builder()
                        .field(field)
                        .operator(operator)
                        .value(value)
                        .build());
            }
        }

        // Construir el DTO
        return GenericFilterDTO.builder()
                .filters(filters)
                .orderBy(params.get("orderBy")) // Campo de ordenamiento
                .order(params.get("order"))    // Dirección del orden (asc o desc)
                .build();
    }
}
