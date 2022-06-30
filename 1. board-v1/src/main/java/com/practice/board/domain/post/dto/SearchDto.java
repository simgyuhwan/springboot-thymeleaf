package com.practice.board.domain.post.dto;

import lombok.Data;

@Data
public class SearchDto {
    private String searchBy;
    private String searchQuery;

    public String addWhereQuery(){
        return "p."+ searchBy ;
    }

    public String getSearchByToUpper(){
        return searchBy.toUpperCase();
    }
}
