package com.app.controlefinanceiro.service.expense.impl;

import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.repository.expense.ExpenseRepository;
import com.app.controlefinanceiro.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Override
    public Expense createExpense(Double value, String description, String category) {
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
    public List updateExpense(String partOfDescription) {
        List<Expense> expenses = repository.findAll();
        List<Expense> foundedExpenses = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getDescription().contains(partOfDescription)) {
                foundedExpenses.add(e);
            }
        }
        return foundedExpenses;
    }

    @Override
    public List<Expense> findAll() {
        return repository.findAll();
    }
}