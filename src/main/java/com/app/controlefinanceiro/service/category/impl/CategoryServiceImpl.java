package com.app.controlefinanceiro.service.category.impl;

import com.app.controlefinanceiro.dto.category.CategoryDto;
import com.app.controlefinanceiro.model.category.Category;
import com.app.controlefinanceiro.repository.category.CategoryRepository;
import com.app.controlefinanceiro.service.category.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public CategoryDto editCategoryName(Long id, CategoryDto dto) {
        Optional<Category> obj = repository.findById(id); //metodo usado para evitar acessar o banco duas vezes na atualização
        Category category = obj.get();
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDto(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> list = repository.findAll();
        List<CategoryDto> listDto = new ArrayList<>();
        for (Category cat : list) {
            listDto.add(new CategoryDto(cat));
        }
        return listDto;
    }

    @Override
    public CategoryDto insert(CategoryDto dto) {
        List<Category> categoryList = repository.findAll();

        for (Category cat : categoryList) {
            if (cat.getName().equals(dto.getName()))
                throw new RuntimeException("This category already exists");
        }

        Category newCategory = new Category();
        newCategory.setName(dto.getName());
        newCategory = repository.save(newCategory);

        return new CategoryDto(newCategory);
    }

    @Override
    public CategoryDto findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category category = obj.
                            orElseThrow(() -> new EntityNotFoundException("Entity not founded"));
        return new CategoryDto(category);
    }

}
