package com.app.controlefinanceiro.service.expense.impl;

import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.repository.expense.ExpenseRepository;
import com.app.controlefinanceiro.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Override
    public Expense createExpense(BigDecimal value, String description, String category) {
        Expense expense = new Expense();
        expense.setValue(value);
        expense.setDescription(description);
        expense.setCategory(category);
        return repository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Expense updateExpense(String partOfDescription) {

        Optional<Expense> obj = repository.findByDescription(partOfDescription);
    }

    @Override
    public List<Expense> findAll() {
        return List.of();
    }
}
