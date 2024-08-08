package com.app.controlefinanceiro.dto.category;

import com.app.controlefinanceiro.model.category.Category;

public class CategoryDto {

     private Long id;
     private String name;
     private Long userId;
     private String type;

    public CategoryDto() {
    }

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.userId = entity.getUserId();
        this.type = entity.getType();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
