package com.POC.views.views.userOfficeView.repository;

import com.POC.views.views.userOfficeView.model.UserOfficeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserOfficeViewRepository extends JpaRepository<UserOfficeView, Integer>, JpaSpecificationExecutor<UserOfficeView> {
}
