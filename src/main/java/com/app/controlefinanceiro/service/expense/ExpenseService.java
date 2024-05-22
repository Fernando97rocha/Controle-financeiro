package com.app.controlefinanceiro.service.expense;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    ExpenseDto createExpense(ExpenseDto dto);
    void deleteExpense(ExpenseDto dto);
    ExpenseDto updateExpense(Long id, ExpenseDto dto);
    List<ExpenseDto> findAllByUserId();
    ExpenseDto findByIdAndUserId(Long id);
}
