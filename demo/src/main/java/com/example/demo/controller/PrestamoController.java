package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Prestamo;
import com.example.demo.service.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public void realizarPrestamo(@RequestParam Long usuarioId, @RequestParam Long libroId) {
        prestamoService.realizarPrestamo(usuarioId, libroId);
    }

    @PostMapping("/devolucion")
    public void devolverLibro(@RequestParam Long prestamoId) {
        prestamoService.devolverLibro(prestamoId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Prestamo> listarPrestamosPorUsuario(@PathVariable Long usuarioId) {
        return prestamoService.listarPrestamosPorUsuario(usuarioId);
    }
}
