package com.POC.views.views.userOfficeView.service;

import com.POC.views.views.userOfficeView.dto.OfficeFilterDTO;
import com.POC.views.views.userOfficeView.model.UserOfficeView;
import com.POC.views.views.userOfficeView.repository.UserOfficeViewRepository;
import com.POC.views.views.userOfficeView.specification.OfficeSpecification;
import com.POC.views.views.userPermissionView.model.UserPermissionView;
import com.POC.views.views.userPermissionView.repository.UserPermissionViewRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserOfficeViewService {

    private final UserOfficeViewRepository repository;

    public List<UserOfficeView> findAll(){
        return repository.findAll();
    }

    public Optional<UserOfficeView> findById(Integer id){
        return repository.findById(id);
    }

    public List<UserOfficeView> findFiltered(OfficeFilterDTO filterDTO) {
        Specification<UserOfficeView> spec = OfficeSpecification.getSpecifications(filterDTO);
        return repository.findAll(spec);
    }

}
