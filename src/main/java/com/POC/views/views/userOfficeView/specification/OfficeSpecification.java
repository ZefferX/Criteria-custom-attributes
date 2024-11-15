package com.POC.views.views.userOfficeView.specification;


import com.POC.views.views.userOfficeView.dto.OfficeFilterDTO;
import com.POC.views.views.userOfficeView.model.UserOfficeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class OfficeSpecification {

    public static Specification<UserOfficeView> getSpecifications(OfficeFilterDTO filterDTO) {
        Specification<UserOfficeView> spec = Specification.where(null);

        if (filterDTO.officeName() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("OFFICE_NAME"), filterDTO.officeName() + "%"));
        }

        if (filterDTO.userName() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("USER_NAME"), filterDTO.userName() + "%"));
        }

        return spec;
    }
}
