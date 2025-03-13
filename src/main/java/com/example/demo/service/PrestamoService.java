package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Libro;
import com.example.demo.model.Prestamo;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.PrestamoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public void realizarPrestamo(Long usuarioId, Long libroId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .map(Usuario.class::cast)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getPrestamos() != null) {
            throw new RuntimeException("El usuario ya tiene un préstamo activo");
        }

        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        if (!libro.isDisponible()) {
            throw new RuntimeException("El libro no está disponible");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setActivo(true);

        libro.setDisponible(false);
        prestamoRepository.save(prestamo);
    }

    @Transactional
    public void devolverLibro(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if (prestamo.getFechaDevolucion() != null) {
            throw new RuntimeException("El préstamo ya fue devuelto");
        }

        prestamo.setFechaDevolucion(LocalDate.now());
        prestamo.getLibro().setDisponible(true);
        prestamo.setActivo(true);
        prestamoRepository.save(prestamo);
    }

    // Listar todos los préstamos por usuario (activos e históricos)
    public List<Prestamo> listarPrestamosPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }
}
