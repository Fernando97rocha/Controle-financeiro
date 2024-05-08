package com.app.controlefinanceiro.service.category;

import com.app.controlefinanceiro.model.category.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public Category createNewCategory(String name);
    public void delete(Long id);
    public Category editCategoryName(Long id, Category category);
    public List<Category> findAll();
}
