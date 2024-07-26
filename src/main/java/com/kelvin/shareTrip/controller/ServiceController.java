package com.kelvin.shareTrip.controller;

import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {
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
}
