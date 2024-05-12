package com.app.controlefinanceiro.service.income;

import com.app.controlefinanceiro.dto.income.IncomeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncomeService {

    IncomeDto createIncome(IncomeDto dto);
    void deleteIncome(IncomeDto dto);
    IncomeDto updateIncome(Long id, IncomeDto dto);
    List<IncomeDto> findAll();
    IncomeDto findById(Long id);

}
