package com.kelvin.shareTrip.controller;

import com.kelvin.shareTrip.model.Relato;
import com.kelvin.shareTrip.service.RelatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {
    @Autowired
    private RelatoService relatoService;
    
    @GetMapping("/relato/all")
    public List<Relato> getAll() {
        return relatoService.getAllRelato();
    };

    @GetMapping("/relato/{id}")
    public Relato getById(@PathVariable String id) {
        return relatoService.getRelatoById(id);
    };

    @PostMapping("/relato/add")
    public Relato add(@RequestBody Relato relato){
        return relatoService.addRelato(relato);
    };

    @DeleteMapping("/relato/delete/{id}")
    public Relato delete(@PathVariable String id) {
        return relatoService.deleteRelato(id);
    };

    @PutMapping("/relato/update/{id}")
    public Relato update(@PathVariable String id, @RequestBody Relato relato) {
        return relatoService.updateRelato(id, relato);
    };
}
