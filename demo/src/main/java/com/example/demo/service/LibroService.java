package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Libro;
import com.example.demo.repository.LibroRepository;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> listarLibrosDisponibles() {
        return libroRepository.findByDisponibleTrue();
    }

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void actualizarLibro(Long id, Libro datosActualizados) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.setTitulo(datosActualizados.getTitulo());
        libro.setAutor(datosActualizados.getAutor());
        libro.setGenero(datosActualizados.getGenero());
        libro.setDisponible(datosActualizados.isDisponible());
        libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
