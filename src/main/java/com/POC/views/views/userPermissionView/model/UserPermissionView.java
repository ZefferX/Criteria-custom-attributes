package com.POC.views.views.userPermissionView.model;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "user_permissions_view")
@Data
public class UserPermissionView {
    @Id
    private Long id;

    @Column(name = "permission_id")
    private Long permissionId;

    private Integer USER_ID;
    private String USER_NAME;
    private String PERMISSION_NAME;
}
