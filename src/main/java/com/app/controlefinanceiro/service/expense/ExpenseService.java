package com.app.controlefinanceiro.service.expense;

import com.app.controlefinanceiro.model.expense.Expense;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {

    Expense createExpense(Expense expense);
    void deleteExpense(Long id);
    List<Expense> updateExpense(String partOfDescription);
    List<Expense> findAll();
}
