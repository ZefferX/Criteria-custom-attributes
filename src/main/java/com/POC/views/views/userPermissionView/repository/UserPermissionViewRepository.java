package com.POC.views.views.userPermissionView.repository;

import com.POC.views.views.userPermissionView.model.UserPermissionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPermissionViewRepository extends JpaRepository<UserPermissionView, Long>, JpaSpecificationExecutor<UserPermissionView> {

    @Query("SELECT u FROM UserPermissionView u WHERE u.permissionId = :permissionId")
    List<UserPermissionView> findByPermissionId(@Param("permissionId") Long permissionId);
}
