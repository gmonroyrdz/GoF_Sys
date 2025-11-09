package com.gofsys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import business.DepartamentoService;
import dal.entity.Departamento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService service;

    @Autowired
    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Departamento> all() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public List<dal.entity.Empleado> findById(@PathVariable int id) {
        return service.searchById(id);
    }
}
