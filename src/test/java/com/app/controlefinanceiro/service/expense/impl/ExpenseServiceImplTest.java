package com.app.controlefinanceiro.service.expense.impl;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import com.app.controlefinanceiro.model.expense.Expense;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        dto.setValue(200.00);
        dto.setCategoryId(1L);
        dto.setDescription("Pet");

        Expense expense = new Expense(dto);
        expense.setId(1L);

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
    void shouldDeleteAnExpenseWhenExists() {

        Long userId = expenseService.getCurrentUserId();

        Expense expense = new Expense();
        expense.setId(1L);
        expense.setDescription("Pet");
        expense.setValue(200.00);
        expense.setCategoryId(1L);
        expenseRepository.save(expense);

        ExpenseDto dto = new ExpenseDto(expense);

        when(expenseRepository.findByIdAndUserId(userId, expense.getId())).thenReturn(Optional.of(expense));

        expenseService.deleteExpense(dto);

        Assertions.assertFalse(expenseRepository.existsById(expense.getId()));
        Assertions.assertTrue(expenseRepository.findAll().isEmpty());

    }

    @Test
    void updateExpense() {
    }

    @Test
    void findAllByUserId() {
    }

    @Test
    void findByIdAndUserId() {
    }
}