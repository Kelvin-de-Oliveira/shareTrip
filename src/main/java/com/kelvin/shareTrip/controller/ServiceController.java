package com.kelvin.shareTrip.controller;

import com.kelvin.shareTrip.model.Relato;
import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.model.Autenticacao;
import com.kelvin.shareTrip.model.Comentario;
import com.kelvin.shareTrip.model.Destino;
import com.kelvin.shareTrip.service.DestinoService;
import com.kelvin.shareTrip.service.InteracaoService;
import com.kelvin.shareTrip.service.UsuarioService;
import com.kelvin.shareTrip.service.RelatoService;
import com.kelvin.shareTrip.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {

    //Usuario

    @Autowired
    private UsuarioService usuarioService;
   
    @GetMapping("/usuario/all")
    public ResponseEntity<?> getAllUsuario() {
        List<Usuario> usuarios = usuarioService.getAllUsuario();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe nenhum usuário cadastrado!");
        } else {
            return ResponseEntity.ok(usuarios);
        }
    };

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable String id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não foi encontrado!");
        } else {
            return ResponseEntity.ok(usuario);
        }
    };

    @PostMapping("/usuario/add")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario addedUsuario = usuarioService.addUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };

    @DeleteMapping("/usuario/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable String id) {
        Usuario usuario = usuarioService.deleteUsuario(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não foi encontrado!");
        } else {
            return ResponseEntity.ok(usuario);
        }
    };

    @PutMapping("/usuario/update/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        try {
            Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.ok(updatedUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };


    // Autenticacao
    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/autenticar")
    public ResponseEntity<String> autenticarUsuario(@RequestBody Autenticacao autenticacao) {
        boolean autenticado = autenticacaoService.autenticarUsuario(autenticacao);
        if (autenticado) {
            return ResponseEntity.ok("Usuário autenticado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos!");
        }
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
    public List<Relato> getAllRelato() {
        return relatoService.getAllRelato();
    };

    @GetMapping("/relato/{id}")
    public Relato getRelatoById(@PathVariable String id) {
        return relatoService.getRelatoById(id);
    };

    @PostMapping("/relato/add")
    public Relato addRelato(@RequestBody Relato relato){
        return relatoService.addRelato(relato);
    };

    @DeleteMapping("/relato/delete/{id}")
    public Relato deleteRelato(@PathVariable String id) {
        return relatoService.deleteRelato(id);
    };

    @PutMapping("/relato/update/{id}")
    public Relato updateRelato(@PathVariable String id, @RequestBody Relato relato) {
        return relatoService.updateRelato(id, relato);
    };

    //Interacao
    
    @Autowired
    private InteracaoService interacaoService;


    @GetMapping("/usuario/interacao/view-usuario/{usuarioId}")
    public List<Relato> verRelatosUsuario(@PathVariable String usuarioId) {
        return interacaoService.verRelatosUsuario(usuarioId);
    };

    @GetMapping("/usuario/interacao/view-destino/{destinoId}")
    public List<Relato> verRelatosDestino(@PathVariable String destinoId) {
        return interacaoService.verRelatosDestino(destinoId);
    };

    @PostMapping("/publicacao/comentar/{relatoId}")
   public ResponseEntity<?> comentarRelato(@PathVariable("relatoId") String id, @RequestBody Comentario comentario) {
        try {
            Comentario savedComentario = interacaoService.comentarRelato(id, comentario);
            return ResponseEntity.ok(savedComentario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    };

    @PostMapping("/publicacao/curtir/{id}")
    public Relato curtirRelato(@PathVariable String id) {
        return interacaoService.curtirRelato(id);
    };



    @PostMapping("/interacao/seguir/{idSeguidor}/{idSeguido}")
    public ResponseEntity<String> seguirUsuario(@PathVariable String idSeguidor, @PathVariable String idSeguido) {
        try {
            interacaoService.seguir(idSeguidor, idSeguido);
            return  ResponseEntity.ok("Usuário agora segue o outro com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };

    @DeleteMapping("/interacao/deixarDeSeguir/{idSeguidor}/{idSeguido}")
    public ResponseEntity<String> deixarDeSeguirUsuario(@PathVariable String idSeguidor, @PathVariable String idSeguido) {
        try {
            interacaoService.deixarDeSeguir(idSeguidor, idSeguido);
            return ResponseEntity.ok("Usuário deixou de seguir o outro com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };
}
