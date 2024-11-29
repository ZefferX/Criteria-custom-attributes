package com.POC.views.views.userPermissionView.repository;

import com.POC.views.views.userPermissionView.model.UserPermissionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserPermissionViewRepository extends JpaRepository<UserPermissionView, Long>, JpaSpecificationExecutor<UserPermissionView> {

}
