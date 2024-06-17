package com.app.controlefinanceiro.dto.category;

import com.app.controlefinanceiro.model.category.Category;

public class CategoryDto {

     private Long id;
     private String name;
     private Long userId;

    public CategoryDto() {
    }

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.userId = entity.getUserId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
