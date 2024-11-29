package com.POC.views.views.userOfficeView.service;

import com.POC.views.views.userOfficeView.model.UserOfficeView;
import com.POC.views.views.userOfficeView.repository.UserOfficeViewRepository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserOfficeViewService {

    private final UserOfficeViewRepository repository;

    public List<UserOfficeView> findAll(){
        return repository.findAll();
    }

    public Page<UserOfficeView> findByGenericFilter(Specification<UserOfficeView> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }


}
