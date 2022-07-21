package com.example.jpa2.domains.category.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddItemRequest {
    @Length(min = 3)
    private String name;
    @Length(min = 3)
    private String imagePath;
    @Min(0)
    private int price;
    @Min(0)
    private int stockQuantity;
    private Long categoryId;
}
