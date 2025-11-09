package com.gofsys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dal.DepartamentoDao;
import dal.entity.Departamento;
import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoDao dao = new DepartamentoDao();

    @GetMapping
    public List<Departamento> all() {
        return dao.getAll();
    }

    @GetMapping("/{id}")
    public Departamento findById(@PathVariable int id) {
        return dao.getById(id);
    }
}
