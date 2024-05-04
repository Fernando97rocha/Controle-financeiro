package com.app.controlefinanceiro.repository.category;

import com.app.controlefinanceiro.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName();
}
