package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    /**
     * EnumType.ORDINAL : enum 순서 값을 DB에 저장
     * EnumType.STRING : enum 이름을 DB에 저장
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP
}
