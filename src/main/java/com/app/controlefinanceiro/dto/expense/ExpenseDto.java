package com.app.controlefinanceiro.dto.expense;

import com.app.controlefinanceiro.model.expense.Expense;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class ExpenseDto {

    private Long id;
    private String description;
    private Double value;
    private LocalDateTime creationDate;
    private String category;
    private Long userId;

    public ExpenseDto() {
    }

    public ExpenseDto(Long id, String description, Double value, LocalDateTime creationDate, String category, Long userId) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.creationDate = creationDate;
        this.category = category;
        this.userId = userId;
    }

    public ExpenseDto(Expense expense) {
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.creationDate = expense.getCreationDate();
        this.category = expense.getCategory();
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
