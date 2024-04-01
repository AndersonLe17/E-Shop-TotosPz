package com.totospz.eshop.config.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class EntityResponse<T> {
    private Integer code;
    private String status;
    private String message;
    private T payload;
    private List<ErrorResponse> errors;
}
