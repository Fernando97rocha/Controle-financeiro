package com.app.controlefinanceiro.service.category.impl;

import com.app.controlefinanceiro.dto.category.CategoryDto;
import com.app.controlefinanceiro.model.category.Category;
import com.app.controlefinanceiro.model.user.User;
import com.app.controlefinanceiro.repository.category.CategoryRepository;
import com.app.controlefinanceiro.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Optional<Category> obj = repository.findById(id);
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

        Category newCategory = new Category();
        newCategory.setName(dto.getName());
        newCategory.setUserId(getCurrentUserId());
        newCategory.setType(dto.getType());

        newCategory = repository.save(newCategory);

        return new CategoryDto(newCategory);
    }

    @Override
    public CategoryDto findById(Long id) {
        return null;
    }

    @Override
    public List<CategoryDto> findAllByUserId() {
        Long userId = getCurrentUserId();

        List<Category> categoriesByUserId = repository.findAllByUserId(userId);
        List<CategoryDto> dtoList = new ArrayList<>();

        for (Category c : categoriesByUserId) {
            dtoList.add(new CategoryDto(c));
        }
        return dtoList;
    }

    public Long getCurrentUserId () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

}
