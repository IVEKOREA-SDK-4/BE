package com.ivekorea.ivekorea_be.random.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BenefitInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "benefit_id")
    private Benefit benefit;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer salePrice;

}
