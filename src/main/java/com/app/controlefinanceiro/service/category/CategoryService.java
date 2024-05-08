package com.app.controlefinanceiro.service.category;

import com.app.controlefinanceiro.dto.category.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDto editCategoryName(Long id, CategoryDto dto);
    List<CategoryDto> findAll();
    CategoryDto insert(CategoryDto dto);
    CategoryDto findById(Long id);
}
