package com.app.controlefinanceiro.repository.expense;

import com.app.controlefinanceiro.model.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {;
    List<Expense> findByUserId(Long id);
}




