package com.kelvin.shareTrip.controller;

import com.kelvin.shareTrip.model.Relato;
import com.kelvin.shareTrip.service.RelatoService;
import com.kelvin.shareTrip.model.Destino;
import com.kelvin.shareTrip.service.DestinoService;
import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {

    //Usuario

    @Autowired
    private UsuarioService usuarioService;
   
    @GetMapping("/usuario/all")
    public List<Usuario> getAll() {
        return usuarioService.getAllUsuario();
    };

    @GetMapping("/usuario/{id}")
    public Usuario getById(@PathVariable String id) {
        return usuarioService.getUsuarioById(id);
    };

    @PostMapping("/usuario/add")
    public Usuario add(@RequestBody Usuario usuario){
        return usuarioService.addUsuario(usuario);
    };

    @DeleteMapping("/usuario/delete/{id}")
    public Usuario delete(@PathVariable String id) {
        return usuarioService.deleteUsuario(id);
    };

    @PutMapping("/usuario/update/{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    };

    // Destino 

    @Autowired
    private DestinoService destinoService;
    
    @GetMapping("/destino/all")
    public List<Destino> getAllDestino() {
        return destinoService.getAllDestino();
    };

    @GetMapping("/destino/{id}")
    public Destino getDestinoById(@PathVariable String id) {
        return destinoService.getDestinoById(id);
    };

    @PostMapping("/destino/add")
    public Destino addDestino(@RequestBody Destino destino){
        return destinoService.addDestino(destino);
    };

    @DeleteMapping("/destino/delete/{id}")
    public Destino deleteDestino(@PathVariable String id) {
        return destinoService.deleteDestino(id);
    };

    @PutMapping("/destino/update/{id}")
    public Destino updateDestino(@PathVariable String id, @RequestBody Destino destino) {
        return destinoService.updateDestino(id, destino);
    };

    //Relato

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
