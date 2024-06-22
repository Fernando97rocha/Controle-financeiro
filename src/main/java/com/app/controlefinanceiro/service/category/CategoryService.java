package com.app.controlefinanceiro.service.category;

import com.app.controlefinanceiro.dto.category.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    CategoryDto editCategoryName(Long id, CategoryDto dto);
    List<CategoryDto> findAll();
    CategoryDto insert(CategoryDto dto);
    CategoryDto findById(Long id);
    List<CategoryDto> findAllByUserId();
}
