package com.app.controlefinanceiro.model.expense;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "expense_table")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "expense_value")
    private Double value;
    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;
    @Column(name = "categoryId")
    private Long categoryId;
    @Column(name = "user_id")
    private Long userId;

    public Expense() {
    }

    public Expense(ExpenseDto dto) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) &&
                Objects.equals(description, expense.description) &&
                Objects.equals(value, expense.value) &&
                Objects.equals(creationDate, expense.creationDate) &&
                Objects.equals(categoryId, expense.categoryId) &&
                Objects.equals(userId, expense.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, value, creationDate, categoryId, userId);
    }
}
