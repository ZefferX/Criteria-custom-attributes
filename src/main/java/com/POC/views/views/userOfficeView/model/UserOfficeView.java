package com.POC.views.views.userOfficeView.model;

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

    private Integer USER_ID;
    private String USER_NAME;
    private String OFFICE_NAME;
}
