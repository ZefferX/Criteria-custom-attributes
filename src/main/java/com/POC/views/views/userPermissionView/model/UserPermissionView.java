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

    @Column(name = "PERMISSION_ID")
    private Long permissionId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PERMISSION_NAME")
    private String permissionName;
}
