package com.ivekorea.ivekorea_be.random.entity;

import com.ivekorea.ivekorea_be.random.level.Level;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Level level;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String name;

    @Column
    private Integer maxCount;

}
