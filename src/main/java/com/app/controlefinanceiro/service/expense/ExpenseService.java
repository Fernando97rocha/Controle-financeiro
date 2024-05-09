package com.app.controlefinanceiro.service.expense;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import com.app.controlefinanceiro.model.expense.Expense;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {

    ExpenseDto createExpense(ExpenseDto dto);
    void deleteExpense(ExpenseDto dto);
    List<ExpenseDto> updateExpense(ExpenseDto dto);
    List<ExpenseDto> findAll();
}
