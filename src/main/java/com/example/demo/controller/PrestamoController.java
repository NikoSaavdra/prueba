package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Prestamo;
import com.example.demo.service.PrestamoService;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void realizarPrestamo(@RequestParam Long usuarioId, @RequestParam Long libroId) {
        prestamoService.realizarPrestamo(usuarioId, libroId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/devolucion")
    public void devolverLibro(@RequestParam Long prestamoId) {
        prestamoService.devolverLibro(prestamoId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{usuarioId}")
    public List<Prestamo> listarPrestamosPorUsuario(@PathVariable Long usuarioId) {
        return prestamoService.listarPrestamosPorUsuario(usuarioId);
    }
}
