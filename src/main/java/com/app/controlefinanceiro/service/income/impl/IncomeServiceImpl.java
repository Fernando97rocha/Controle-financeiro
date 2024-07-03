package com.app.controlefinanceiro.service.income.impl;

import com.app.controlefinanceiro.dto.income.IncomeDto;
import com.app.controlefinanceiro.model.income.Income;
import com.app.controlefinanceiro.model.user.User;
import com.app.controlefinanceiro.repository.income.IncomeRepository;
import com.app.controlefinanceiro.service.income.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        if (dto.getDescription() != null) income.setDescription(dto.getDescription());
            else throw new RuntimeException("Income description is required");
        if (dto.getValue() != null)income.setValue(dto.getValue());
            else throw new RuntimeException("Income amount is required");
        income.setCategoryId(dto.getCategoryId());
        income.setCreationDate(LocalDateTime.now());
        income.setUserId(getCurrentUserId());

        repository.save(income); // income não precisou receber o save para receber o id, por exemplo

        return new IncomeDto(income);
    }

    @Override
    public void deleteIncome(IncomeDto dto) {
        Long userId = getCurrentUserId();

        Optional<Income> obj = repository.findByIdAndUserId(dto.getId(), userId);
        obj.ifPresent(repository::delete);
    }

    @Override
    public IncomeDto updateIncome(Long id, IncomeDto dto) {
        Long userId = getCurrentUserId();

        Optional<Income> obj = repository.findByIdAndUserId(id, userId);

        Income income = obj.get();
        income.setValue(dto.getValue());
        income.setCategoryId(dto.getCategoryId());
        income.setDescription(dto.getDescription());
        income = repository.save(income);
        return new IncomeDto(income);
    }

    @Override
    public List<IncomeDto> findAllByUserId() {
        Long userId = getCurrentUserId();

        List<Income> incomes = repository.findByUserId(userId);
        List<IncomeDto> dtoList = new ArrayList<>();
        for (Income i : incomes)
            dtoList.add(new IncomeDto(i));
        return dtoList;
    }

    @Override
    public IncomeDto findById(Long id) {
        Long userId = getCurrentUserId();

        Optional<Income> obj = repository.findByIdAndUserId(id, userId);
        Income income = obj.orElseThrow();
        return new IncomeDto(income);
    }

    //metodo para associar o userId do usuario do contexto atual ao objeto income criado
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

}
