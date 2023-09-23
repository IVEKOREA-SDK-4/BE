package com.ivekorea.ivekorea_be.random.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    public BenefitInfo(Benefit benefit, String productName, String imageUrl, Integer salePrice) {
        this.benefit = benefit;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.salePrice = salePrice;
    }
}
