package com.app.controlefinanceiro.model.income;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "income_table")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "income_value")
    private Double value;
    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "user_id")
    private Long userId;

    public Income() {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return Objects.equals(id, income.id) &&
                Objects.equals(description, income.description) &&
                Objects.equals(value, income.value) &&
                Objects.equals(creationDate, income.creationDate) &&
                Objects.equals(categoryId, income.categoryId) &&
                Objects.equals(userId, income.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, value, creationDate, categoryId, userId);
    }
}
