package com.POC.views.views.userPermissionView.dto;

import lombok.Builder;

@Builder
public record UserPermissionFilterDTO(
        String permissionName,
        String userName) {
}
