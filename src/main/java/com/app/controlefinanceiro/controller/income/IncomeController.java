package com.app.controlefinanceiro.controller.income;

import com.app.controlefinanceiro.dto.income.IncomeDto;
import com.app.controlefinanceiro.model.income.Income;
import com.app.controlefinanceiro.service.income.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/incomes")
public class IncomeController {

    @Autowired
    IncomeService service;

    @PostMapping
    public ResponseEntity<IncomeDto> insert(@RequestBody IncomeDto dto) {
        dto = service.createIncome(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<IncomeDto> update(@PathVariable Long id, @RequestBody IncomeDto dto) {
        dto = service.updateIncome(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable long id, IncomeDto dto) {
        service.deleteIncome(dto);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<IncomeDto>> findAll() {
        List<IncomeDto> dtoList = service.findAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IncomeDto> findIncome(@PathVariable Long id) {
        IncomeDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
