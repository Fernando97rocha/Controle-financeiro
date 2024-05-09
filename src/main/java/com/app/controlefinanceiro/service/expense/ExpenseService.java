package com.app.controlefinanceiro.service.expense;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import com.app.controlefinanceiro.model.expense.Expense;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {

    ExpenseDto createExpense(ExpenseDto dto);
    void deleteExpense(ExpenseDto dto);
    ExpenseDto updateExpense(Long id, ExpenseDto dto);
    List<ExpenseDto> findAll();
    ExpenseDto findById(Long id);
}
