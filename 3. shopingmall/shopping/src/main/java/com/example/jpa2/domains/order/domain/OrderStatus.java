package com.example.jpa2.domains.order.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
public enum OrderStatus {
    ORDERED_STATUS("주문 상태"),
    CANCEL_STATUS("주문 취소");

    private String status;

    OrderStatus(String status){
        this.status = status;
    }
}
