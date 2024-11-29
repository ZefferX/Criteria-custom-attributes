package com.POC.views.views.userPermissionView.service;


import com.POC.views.views.userPermissionView.model.UserPermissionView;
import com.POC.views.views.userPermissionView.repository.UserPermissionViewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public Page<UserPermissionView> findByGenericFilter(Specification<UserPermissionView> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

}
