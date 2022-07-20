package com.example.jpa2.domains.order.domain;

import com.example.jpa2.common.config.logging.BaseEntity;
import com.example.jpa2.domains.delivery.domain.DeliveryEntity;
import com.example.jpa2.domains.delivery.domain.DeliveryStatus;
import com.example.jpa2.domains.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Table(name = "orders")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity orderer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryEntity deliveryInformation;

    @OneToMany
    @JoinColumn(name = "order_item_id")
    private List<OrderItemEntity> orderItemList;

    @Builder
    public OrderEntity(MemberEntity orderer, DeliveryEntity deliveryInformation,
                       OrderItemEntity... orderItemEntityList){
        this.orderer = orderer;
        this.deliveryInformation = deliveryInformation;

    }

    private void setOrderItemList(OrderItemEntity... orderItemEntityList){
        Arrays.stream(orderItemEntityList)
                .forEach(orderItemEntity -> this.orderItemList.add(orderItemEntity));
        this.calculateTotalAmount();
    }

    private void calculateTotalAmount(){
        this.totalAmount = orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getOrderItemAmount())
                .sum();
    }

    // business logic
    public void cancel(){
        if(this.deliveryInformation.getStatus() == DeliveryStatus.COMPLETE_STATUS
                 || this.deliveryInformation.getStatus() == DeliveryStatus.SHIPPING_STATUS) {
            throw new IllegalStateException("이미 배송중이거나 배송이 완료된 주문은 취소가 불가능합니다.");
        }

        this.orderItemList.stream()
                .forEach(orderItem -> orderItem.cancel());

        this.status = OrderStatus.CANCEL_STATUS;
    }

}
