package com.POC.views.views.criteria.Specification;

import com.POC.views.views.criteria.dto.GenericFilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification <T> {
    public static <T> Specification<T> getSpecification(GenericFilterDTO dto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Procesar filtros
            if (dto.filters() != null) {
                for (GenericFilterDTO.Filter filter : dto.filters()) {
                    String field = filter.field();
                    String operator = filter.operator();
                    String value = filter.value();

                    switch (operator.toLowerCase()) {
                        case "eq": // Igual
                            predicates.add(criteriaBuilder.equal(root.get(field), value));
                            break;
                        case "like": // LIKE
                            predicates.add(criteriaBuilder.like(root.get(field), value + "%"));
                            break;
                        case "gt": // Mayor que
                            predicates.add(criteriaBuilder.greaterThan(root.get(field), value));
                            break;
                        case "lt": // Menor que
                            predicates.add(criteriaBuilder.lessThan(root.get(field), value));
                            break;
                        case "gte": // Mayor o igual
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(field), value));
                            break;
                        case "lte": // Menor o igual
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(field), value));
                            break;
                        default:
                            throw new IllegalArgumentException("Operador no soportado: " + operator);
                    }
                }
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
