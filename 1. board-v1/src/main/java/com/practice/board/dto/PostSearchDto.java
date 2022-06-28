package com.practice.board.dto;

import lombok.Data;

@Data
public class PostSearchDto {
    private String searchBy;
    private String searchQuery;

    public String addWhereQuery(){
        return "p."+ searchBy ;
    }
}
