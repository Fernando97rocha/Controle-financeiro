package com.app.controlefinanceiro.service.income.impl;

import com.app.controlefinanceiro.dto.income.IncomeDto;
import com.app.controlefinanceiro.model.income.Income;
import com.app.controlefinanceiro.model.user.User;
import com.app.controlefinanceiro.repository.income.IncomeRepository;
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
class IncomeServiceImplTest {

    @InjectMocks
    private IncomeServiceImpl incomeService;

    @Mock
    private IncomeRepository incomeRepository;

    @BeforeEach
    public void setUp() {
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
    void shouldCreateANewIncome() {
        //Arrange
        Long userId = incomeService.getCurrentUserId();
        IncomeDto dto = new IncomeDto();
        dto.setDescription("Salary");
        dto.setValue(3000.00);
        dto.setCategoryId(1L);

        Income income = new Income(dto);

        when(incomeRepository.save(any(Income.class))).thenReturn(income);
        //Act
        IncomeDto result = incomeService.createIncome(dto);
        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto.getDescription(), result.getDescription());
        Assertions.assertEquals(dto.getValue(), result.getValue());
        Assertions.assertNotNull(result.getCreationDate());
        Assertions.assertNotNull(result.getUserId());
        Assertions.assertEquals(userId, result.getUserId());
    }

    @Test
    void shouldDeleteAnIncome() {
        Long userId = incomeService.getCurrentUserId();
        //Arrange
        Income income = new Income();
        income.setId(1L);

        IncomeDto dto = new IncomeDto(income);

        when(incomeRepository.findByIdAndUserId(dto.getId(), userId)).thenReturn(Optional.of(income));
        //Act
        incomeService.deleteIncome(dto);
        //Assert
        verify(incomeRepository, times(1)).delete(income);
    }

    @Test
    void ShouldUpdateIncome() {

        Long userId = incomeService.getCurrentUserId();

        Income existentIncome = new Income();
        existentIncome.setId(1L);
        existentIncome.setDescription("Salary");
        existentIncome.setValue(3000.00);
        existentIncome.setCategoryId(1L);
        existentIncome.setUserId(userId);

        when(incomeRepository.findByIdAndUserId(existentIncome.getId(), userId)).thenReturn(Optional.of(existentIncome));

        IncomeDto dtoToUpdateIncome = new IncomeDto();
        dtoToUpdateIncome.setDescription("Sale");
        dtoToUpdateIncome.setValue(5000.00);

        when(incomeRepository.save(any(Income.class))).thenReturn(existentIncome);

        IncomeDto result = incomeService.updateIncome(existentIncome.getId(), dtoToUpdateIncome);

        Assertions.assertEquals(result.getDescription(), dtoToUpdateIncome.getDescription());
        Assertions.assertEquals(result.getValue(), dtoToUpdateIncome.getValue());
        Assertions.assertEquals(result.getCategoryId(), dtoToUpdateIncome.getCategoryId());
    }

    @Test
    void shouldFindAllIncomesByUserId() {
        Long userId = incomeService.getCurrentUserId();

        IncomeDto dto = new IncomeDto();
        dto.setUserId(1L);

        Income income = new Income(dto);

        when(incomeRepository.findByUserId(userId)).thenReturn(Collections.singletonList(income));

        List<IncomeDto> result = incomeService.findAllByUserId();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        verify(incomeRepository, times(1)).findByUserId(userId);
    }

    @Test
    void shouldFindAnIncomeById() {
        Long userId = incomeService.getCurrentUserId();
        Long id = 5L;

        Income income = new Income();
        income.setId(id);
        income.setUserId(userId);

        when(incomeRepository.findByIdAndUserId(id, userId)).thenReturn(Optional.of(income));

        IncomeDto result = incomeService.findById(id);

        verify(incomeRepository, times(1)).findByIdAndUserId(id, userId);
    }
}