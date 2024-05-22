package com.app.controlefinanceiro.controller.expense;

import com.app.controlefinanceiro.dto.expense.ExpenseDto;
import com.app.controlefinanceiro.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {

    @Autowired
    ExpenseService service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<ExpenseDto>> expenseList () {
        List<ExpenseDto> dtoList = service.findByUserId();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> insert(@RequestBody ExpenseDto dto) {
        dto = service.createExpense(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ExpenseDto> update(@PathVariable Long id, @RequestBody ExpenseDto dto) {
        dto = service.updateExpense(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExpenseDto> findExpense(@PathVariable Long id) {
        ExpenseDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ExpenseDto> delete(@PathVariable Long id, ExpenseDto dto) {
        service.deleteExpense(dto);
        return ResponseEntity.ok(dto);
    }
}
