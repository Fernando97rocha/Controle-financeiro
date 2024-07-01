package com.app.controlefinanceiro.dto.income;

import com.app.controlefinanceiro.model.income.Income;

import java.time.LocalDateTime;

public class IncomeDto {

    private Long id;
    private String description;
    private Double value;
    private LocalDateTime creationDate;
    private Long categoryId;
    private Long userId;

    public IncomeDto() {
    }

    public IncomeDto(Income income) {
        this.id = income.getId();
        this.description = income.getDescription();
        this.value = income.getValue();
        this.creationDate = income.getCreationDate();
        this.categoryId = income.getCategoryId();
        this.userId = income.getUserId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Double getValue() {

        return value;
    }

    public void setValue(Double value) {

        this.value = value;
    }

    public LocalDateTime getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
