package com.app.controlefinanceiro.service.category.impl;

import com.app.controlefinanceiro.model.category.Category;
import com.app.controlefinanceiro.repository.category.CategoryRepository;
import com.app.controlefinanceiro.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category createNewCategory(String name) {
        Category newCategory = new Category(name);
        List<Category> categories = repository.findAll();
        for (Category c : categories)
            if (c.getName().equals(newCategory.getName()))
                return c;
        return repository.save(newCategory);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Category editCategoryName(Long id, Category renamedCategory) {
        Category currentCategory;
        Optional<Category> obj = repository.findById(id);
        currentCategory = obj.get();
        currentCategory.setName(renamedCategory.getName());
        return repository.save(currentCategory);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

}
