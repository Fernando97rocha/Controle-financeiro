package com.app.controlefinanceiro.service.expense.impl;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.repository.expense.ExpenseRepository;
import com.app.controlefinanceiro.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Override
    public ExpenseDto createExpense(ExpenseDto dto) {
        Expense expense = new Expense(
                dto.getId(),
                dto.getDescription(),
                dto.getValue(),
                dto.getCreationDate(),
                dto.getCategory(),
                dto.getUserId()
        );
        expense = repository.save(expense);
        return new ExpenseDto(expense);
    }

    @Override
    public void deleteExpense(ExpenseDto dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public List<ExpenseDto> updateExpense(ExpenseDto dto) {

        return ;
    }

    @Override
    public List<ExpenseDto> findAll() {

        return ;
    }
}
