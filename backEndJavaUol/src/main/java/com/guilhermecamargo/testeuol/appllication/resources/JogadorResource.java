package com.guilhermecamargo.testeuol.appllication.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilhermecamargo.testeuol.domain.entities.Jogador;
import com.guilhermecamargo.testeuol.domain.services.JogadorService;

@RestController
@RequestMapping(value = "/jogadores")
public class JogadorResource {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(jogadorService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return ResponseEntity.ok(this.jogadorService.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> salvar(@RequestBody @Valid  Jogador jogador){

        try {
            this.jogadorService.create(jogador);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
        this.jogadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
