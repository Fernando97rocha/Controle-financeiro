package com.app.controlefinanceiro.dto.expense;

import com.app.controlefinanceiro.model.expense.Expense;

import java.time.LocalDateTime;

public class ExpenseDto {

    private Long id;
    private String description;
    private Double value;
    private LocalDateTime creationDate;
    private Long categoryId;
    private Long userId;

    public ExpenseDto() {
    }

    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.creationDate = expense.getCreationDate();
        this.categoryId = expense.getCategoryId();
        this.userId = expense.getUserId();
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
