package com.example.jpa2.domains.delivery.domain;

import com.example.jpa2.common.config.logging.BaseEntity;
import com.example.jpa2.common.value.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    public DeliveryEntity(Address address){
        this.address = address;
        this.status = DeliveryStatus.READY_STATUS;
    }

}
