package com.POC.views.views.userPermissionView.service;


import com.POC.views.views.userPermissionView.dto.UserPermissionFilterDTO;
import com.POC.views.views.userPermissionView.model.UserPermissionView;
import com.POC.views.views.userPermissionView.repository.UserPermissionViewRepository;
import com.POC.views.views.userPermissionView.specification.UserPermissionSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserPermissionViewService {
    private final UserPermissionViewRepository repository;

    public List<UserPermissionView> findAll(){
        return repository.findAll();
    }

    public List<UserPermissionView> findByPermissionId(Long permissionId){
        return repository.findByPermissionId(permissionId);

    }

    public List<UserPermissionView> findFiltered(UserPermissionFilterDTO filterDTO) {
        Specification<UserPermissionView> spec = UserPermissionSpecification.getSpecifications(filterDTO);
        return repository.findAll(spec);
    }


}
