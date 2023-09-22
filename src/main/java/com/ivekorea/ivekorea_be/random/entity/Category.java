package com.ivekorea.ivekorea_be.random.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}
