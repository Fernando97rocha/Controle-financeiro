package com.app.controlefinanceiro.repositories;

import com.app.controlefinanceiro.model.category.Category;
import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.repository.expense.ExpenseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
public class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository repository;

    @Test
    public void deleteShouldDeletedObjectWhenIdAndUserIdExists () {

        Long userId = 2L;
        Long existingId = 3L;

        Expense newExpense = new Expense();
        newExpense.setUserId(userId);
        newExpense.setId(existingId);
        newExpense.setCreationDate(LocalDateTime.now());
        newExpense.setValue(100.00);
        newExpense.setDescription("Alimentação");
        newExpense.setCategory(new Category("Pet"));

        repository.save(newExpense);

        Optional<Expense> obj = repository.findByIdAndUserId(existingId, userId);
        Expense expense = obj.get();

        repository.delete(expense);

        Assertions.assertFalse(obj.isPresent());

    }
}
