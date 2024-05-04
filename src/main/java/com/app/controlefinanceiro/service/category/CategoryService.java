package com.app.controlefinanceiro.service.category;

import com.app.controlefinanceiro.model.category.Category;
import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.model.income.Income;

import java.util.List;

public interface CategoryService {

    public List<Expense> insertExpenseIntoCategory();
    public List<Income> insertIncomeIntoCategory();
    public void delete();
    public Category editCategoryName();
    public Category createNewCategory();
}
