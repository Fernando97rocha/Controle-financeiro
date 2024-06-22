package com.app.controlefinanceiro.controller.category;

import com.app.controlefinanceiro.dto.category.CategoryDto;
import com.app.controlefinanceiro.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> list = service.findAllByUserId();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> insert(@RequestBody CategoryDto dto) {
        dto = service.insert(dto); // apenas retornando o resultado do insert para uma referencia
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        CategoryDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto dto) {
        dto = service.editCategoryName(id, dto);
        return ResponseEntity.ok().body(dto);
    }

}
