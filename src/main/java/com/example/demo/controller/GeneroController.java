package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Genero;
import com.example.demo.service.GeneroService;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Genero> listarGeneros() {
        return generoService.listarGeneros();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void crearGenero(@RequestBody Genero genero) {
        generoService.crearGenero(genero);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void actualizarGenero(@PathVariable Long id, @RequestParam String nuevoNombre) {
        generoService.actualizarGenero(id, nuevoNombre);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarGenero(@PathVariable Long id) {
        generoService.eliminarGenero(id);
    }
}
