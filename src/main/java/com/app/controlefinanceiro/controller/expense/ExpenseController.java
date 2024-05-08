package com.app.controlefinanceiro.controller.expense;

import com.app.controlefinanceiro.model.expense.Expense;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {



    @GetMapping(value = "/list")
    public ResponseEntity<List<Expense>> expenseList () {
        List<Expense> list = new ArrayList<>();
        return ResponseEntity.ok(list);
    }
}
