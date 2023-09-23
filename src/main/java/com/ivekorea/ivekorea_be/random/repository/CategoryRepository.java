package com.ivekorea.ivekorea_be.random.repository;

import com.ivekorea.ivekorea_be.random.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
