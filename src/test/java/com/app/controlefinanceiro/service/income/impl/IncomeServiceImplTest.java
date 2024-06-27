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
    void ShouldCreateANewIncomeWhenUserIsAuthenticated() {
        //Arrange
        IncomeDto dto = new IncomeDto();
        dto.setDescription("Salary");
        dto.setValue(3000.00);
        dto.setCategoryId(1L);
        //Act
        IncomeDto result = incomeService.createIncome(dto);
        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto.getDescription(), result.getDescription());
        Assertions.assertEquals(dto.getValue(), result.getValue());
        Assertions.assertNotNull(result.getCreationDate());
        Assertions.assertNotNull(result.getUserId());
    }

    @Test
    void ShouldNotCreateANewIncomeWhenAuthenticationDoNotExists () {

        SecurityContextHolder.clearContext();

        IncomeDto dto = new IncomeDto();
        dto.setDescription("Salary");
        dto.setValue(3000.00);

        Assertions.assertThrows(RuntimeException.class, () -> {
            IncomeDto createdIncome = incomeService.createIncome(dto);
        });
    }

    @Test
    void ShouldDeleteIncomeWhenUserIsAuthenticatedAndUserIdIsValid() {
        //Arrange
        Income income = new Income();
        income.setId(1L);
        income.setCategoryId(1L);
        income.setValue(3000.00);
        income.setDescription("Salary");
        incomeRepository.save(income);
        IncomeDto dto = new IncomeDto(income);
        //Act
        incomeService.deleteIncome(dto);
        //Assert
        Assertions.assertFalse(incomeRepository.existsById(dto.getId()));
    }

    @Test
    void ShouldThrowsAnExceptionWhenDeleteIncomeWithoutBeAuthenticated() {
        SecurityContextHolder.clearContext();

        Income income = new Income();
        income.setId(1L);
        income.setCategoryId(1L);
        income.setValue(3000.00);
        income.setDescription("Salary");
        incomeRepository.save(income);
        IncomeDto dto = new IncomeDto(income);

        Assertions.assertThrows(RuntimeException.class, () -> {
            incomeService.deleteIncome(dto);
        });
    }

    @Test
    void updateIncome() {
    }

    @Test
    void findAllByUserId() {
    }

    @Test
    void findById() {
    }

    @Test
    void getCurrentUserId() {
    }
}