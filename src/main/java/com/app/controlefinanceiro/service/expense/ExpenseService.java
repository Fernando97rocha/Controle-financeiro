package com.app.controlefinanceiro.service.expense;

import com.app.controlefinanceiro.model.expense.Expense;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {

    Expense createExpense(BigDecimal value, String description, String category);
    void deleteExpense(Long id);
    Expense updateExpense(String partOfDescription);
    List<Expense> findAll();
}
