package com.POC.views.views.userPermissionView.specification;

import com.POC.views.views.userPermissionView.dto.UserPermissionFilterDTO;
import com.POC.views.views.userPermissionView.model.UserPermissionView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserPermissionSpecification {


    public static Specification<UserPermissionView> getSpecifications(UserPermissionFilterDTO filterDTO) {
        Specification<UserPermissionView> spec = Specification.where(null);

        if (filterDTO.permissionName() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("PERMISSION_NAME"), filterDTO.permissionName() + "%"));
        }

        if (filterDTO.userName() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(root.get("USER_NAME"), filterDTO.userName() + "%"));
        }

        return spec;
    }
}
