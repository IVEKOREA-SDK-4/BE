package com.ivekorea.ivekorea_be.random.entity;

import com.ivekorea.ivekorea_be.random.draw.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    public Benefit(Level level, Category category, String name) {
        this.level = level;
        this.category = category;
        this.name = name;
    }

}
