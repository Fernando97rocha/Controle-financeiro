package com.app.controlefinanceiro.repository.expense;

import com.app.controlefinanceiro.model.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByDescription(String partOfDescription);
}
