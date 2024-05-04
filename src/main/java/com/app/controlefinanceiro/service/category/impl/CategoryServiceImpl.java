package com.app.controlefinanceiro.service.category.impl;

import com.app.controlefinanceiro.model.category.Category;
import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.model.income.Income;
import com.app.controlefinanceiro.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private Expense expense;

    @Override
    public List<Expense> insertExpenseIntoCategory() {

        return List.of();
    }

    @Override
    public List<Income> insertIncomeIntoCategory() {
        return List.of();
    }

    @Override
    public void delete() {

    }

    @Override
    public Category editCategoryName() {
        return null;
    }

    @Override
    public Category createNewCategory() {
        return null;
    }
}
