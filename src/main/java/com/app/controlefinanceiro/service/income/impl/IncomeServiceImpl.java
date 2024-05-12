package com.app.controlefinanceiro.service.income.impl;

import com.app.controlefinanceiro.dto.income.IncomeDto;
import com.app.controlefinanceiro.model.income.Income;
import com.app.controlefinanceiro.repository.income.IncomeRepository;
import com.app.controlefinanceiro.service.income.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    IncomeRepository repository;

    @Override
    public IncomeDto createIncome(IncomeDto dto) {
        Income income = new Income();
        income.setDescription(dto.getDescription());
        income.setValue(dto.getValue());
        income.setCategory(dto.getCategory());
        income.setCreationDate(LocalDateTime.now());
        income = repository.save(income);
        return new IncomeDto(income);
    }

    @Override
    public void deleteIncome(IncomeDto dto) {
        repository.deleteById(dto.getId());
    }

    @Override
    public IncomeDto updateIncome(Long id, IncomeDto dto) {
        Optional<Income> obj = repository.findById(id);
        Income income = obj.get();
        income.setValue(dto.getValue());
        income.setCategory(dto.getCategory());
        income.setDescription(dto.getDescription());
        income = repository.save(income);
        return new IncomeDto(income);
    }

    @Override
    public List<IncomeDto> findAll() {
        List<Income> incomes = repository.findAll();
        List<IncomeDto> dtoList = new ArrayList<>();
        for (Income i : incomes) {
            dtoList.add(new IncomeDto(i));
        }
        return dtoList;
    }

    @Override
    public IncomeDto findById(Long id) {
        Optional<Income> obj = repository.findById(id);
        Income income = obj.get();
        return new IncomeDto(income);
    }
}
