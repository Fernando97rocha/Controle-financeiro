package com.app.controlefinanceiro.service.expense.impl;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.model.user.User;
import com.app.controlefinanceiro.repository.expense.ExpenseRepository;
import com.app.controlefinanceiro.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {


    @Autowired
    private ExpenseRepository repository;

    @Override
    public ExpenseDto createExpense(ExpenseDto dto) {

        Expense expense = new Expense();
        expense.setDescription(dto.getDescription());
        expense.setValue(dto.getValue());
        expense.setCategory(dto.getCategory());
        expense.setCreationDate(LocalDateTime.now());
        expense.setUserId(getCurrentUserId());
        expense = repository.save(expense);

        return new ExpenseDto(expense);
    }

    @Override
    public void deleteExpense(ExpenseDto dto) {
        Long userId = getCurrentUserId();

        Optional<Expense> obj = repository.findByIdAndUserId(dto.getId(), userId);
        Expense expense = obj.get();
        repository.delete(expense);
    }

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto dto) {
        Long userId = getCurrentUserId();

        Optional<Expense> obj = repository.findByIdAndUserId(id, userId);
        Expense expense = obj.get();
        expense.setDescription(dto.getDescription());
        expense.setValue(dto.getValue());
        expense.setCategory(dto.getCategory());
        expense = repository.save(expense);
        return new ExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> findAllByUserId() {
        Long userId = getCurrentUserId();

        List<Expense> expenses = repository.findByUserId(userId);
        List<ExpenseDto> dtoList = new ArrayList<>();
        for (Expense e : expenses) {
            dtoList.add(new ExpenseDto(e));
        }
        return dtoList;
    }

    @Override
    public ExpenseDto findByIdAndUserId(Long id) {
        Long userId = getCurrentUserId();

        Optional<Expense> obj = repository.findByIdAndUserId(id, userId);
        Expense expense = obj.orElseThrow();
        return new ExpenseDto(expense);
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

}
