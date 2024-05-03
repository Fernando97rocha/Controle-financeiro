package com.app.controlefinanceiro.model.expense;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Expense {

    private Long id;
    private String description;
    private BigDecimal value;
    private LocalDateTime creationDate;
    private int category;
    private Long userId;

    public Expense() {
        this.creationDate = LocalDateTime.now();
    }

    public Expense(Long id, String description, BigDecimal value, LocalDateTime creationDate, int category) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.creationDate = LocalDateTime.now();
        this.category = category;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
