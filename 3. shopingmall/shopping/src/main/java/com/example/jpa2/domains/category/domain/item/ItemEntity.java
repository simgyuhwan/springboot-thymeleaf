package com.example.jpa2.domains.category.domain.item;

import com.example.jpa2.common.config.logging.BaseEntity;
import com.example.jpa2.domains.category.service.NotEnoughStockQuantityException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String name;
    private int price;
    private int stockQuantity;
    private Long categoryId;
    private String imagePath;

    @Builder
    public ItemEntity(String name, int price, int stockQuantity, Long categoryId, String imagePath) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.imagePath = imagePath;
    }

    public void removeStockQuantity(int orderQuantity){
        int restStockQuantity = this.stockQuantity -= orderQuantity;

        if(restStockQuantity < 0){
            throw new NotEnoughStockQuantityException();
        }

        this.stockQuantity = restStockQuantity;
    }

    public void addStockQuantity(int quantity){
        this.stockQuantity += quantity;
    }

}
