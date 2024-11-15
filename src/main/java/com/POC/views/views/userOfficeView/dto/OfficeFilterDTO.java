package com.POC.views.views.userOfficeView.dto;

import lombok.Builder;

@Builder
public record OfficeFilterDTO(
        String officeName,
        String userName
) {
}
