package com.POC.views.views.userOfficeView.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "user_offices_view")
@Data
public class UserOfficeView {
    @Id
    private Integer id;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "OFFICE_NAME")
    private String officeName;

    @Column(name = "OFFICE_ID")
    private String officeId;
}
