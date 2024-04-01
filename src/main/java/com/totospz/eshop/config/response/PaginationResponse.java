package com.totospz.eshop.config.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class PaginationResponse<T> {
    private T data;
    private Integer totalDocs;
    private Integer totalPages;
    private Integer prevPage;
    private Integer nextPage;
    private Integer page;
    private Boolean hasPrevPage;
    private Boolean hasNextPage;
    private URI prevLink;
    private URI nextLink;
}
