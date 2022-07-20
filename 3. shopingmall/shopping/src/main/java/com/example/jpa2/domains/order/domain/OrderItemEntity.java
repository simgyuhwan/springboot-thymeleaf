package com.example.jpa2.domains.order.domain;

import com.example.jpa2.common.config.logging.BaseEntity;
import com.example.jpa2.domains.category.domain.item.ItemEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private int orderCount;
    private int orderItemAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Builder
    private OrderItemEntity(ItemEntity item, int orderCount){
        this.item = item;
        this.orderCount = orderCount;
        this.calculateOrderItemAmount();
    }

    private void calculateOrderItemAmount(){
        this.orderItemAmount = item.getPrice() * orderCount;
    }

    public void removeStockQuantity(){
        this.item.removeStockQuantity(orderCount);
    }

    public void cancel(){
        this.item.addStockQuantity(this.orderCount);
    }

}
