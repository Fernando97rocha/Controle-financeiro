package com.app.controlefinanceiro.service.expense.impl;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import com.app.controlefinanceiro.dto.income.IncomeDto;
import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.model.income.Income;
import com.app.controlefinanceiro.model.user.User;
import com.app.controlefinanceiro.repository.expense.ExpenseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Mock
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = new User();
        user.setId(1L);
        user.setLogin("testuser");
        user.setPassword("123456");

        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
    }

    @Test
    void shouldCreateANewExpense() {
        Long userId = expenseService.getCurrentUserId();
        ExpenseDto dto = new ExpenseDto();
        dto.setDescription("Pet");
        dto.setValue(120.00);
        dto.setCategoryId(1L);

        Expense expense = new Expense(dto);

        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);
        //Act
        ExpenseDto result = expenseService.createExpense(dto);
        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto.getDescription(), result.getDescription());
        Assertions.assertEquals(dto.getValue()*-1, result.getValue());
        Assertions.assertNotNull(result.getCreationDate());
        Assertions.assertNotNull(result.getUserId());
        Assertions.assertEquals(userId, result.getUserId());
    }

    @Test
    void shouldDeleteAnExpense() {

        Long userId = expenseService.getCurrentUserId();

        Expense expense = new Expense();
        expense.setId(1L);

        ExpenseDto dto = new ExpenseDto(expense);

        when(expenseRepository.findByIdAndUserId(dto.getId(), userId)).thenReturn(Optional.of(expense));

        expenseService.deleteExpense(dto);

        verify(expenseRepository, times(1)).delete(expense);
    }

    @Test
    void shouldUpdateAnExpense() {
        Long userId = expenseService.getCurrentUserId();

        Expense existingExpense = new Expense();
        existingExpense.setId(1L);
        existingExpense.setDescription("Salary");
        existingExpense.setValue(3000.00);
        existingExpense.setCategoryId(1L);

        when(expenseRepository.findByIdAndUserId(existingExpense.getId(), userId)).
                thenReturn(Optional.of(existingExpense));

        ExpenseDto dtoToUpdate = new ExpenseDto();
        dtoToUpdate.setDescription("Extra");
        dtoToUpdate.setValue(500.00);
        dtoToUpdate.setCategoryId(2L);

        when(expenseRepository.save(any(Expense.class))).thenReturn(existingExpense);

        ExpenseDto result = expenseService.updateExpense(existingExpense.getId(), dtoToUpdate);

        Assertions.assertEquals(result.getDescription(), dtoToUpdate.getDescription());
        Assertions.assertEquals(result.getValue(), dtoToUpdate.getValue());
        Assertions.assertEquals(result.getCategoryId(), dtoToUpdate.getCategoryId());
    }

    @Test
    void shouldFindAllExpensesByUserId() {
        Long userId = expenseService.getCurrentUserId();

        ExpenseDto dto = new ExpenseDto();
        dto.setUserId(1L);

        Expense expense = new Expense(dto);

        when(expenseRepository.findByUserId(userId)).thenReturn(Collections.singletonList(expense));

        List<ExpenseDto> result = expenseService.findAllByUserId();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        verify(expenseRepository, times(1)).findByUserId(userId);
    }

    @Test
    void shouldFindAnExpenseById() {

        Long userId = expenseService.getCurrentUserId();
        Long id = 5L;

        Expense expense = new Expense();
        expense.setId(id);
        expense.setUserId(userId);

        when(expenseRepository.findByIdAndUserId(id, userId)).thenReturn(Optional.of(expense));

        ExpenseDto result = expenseService.findById(id);

        verify(expenseRepository, times(1)).findByIdAndUserId(id, userId);
    }
}