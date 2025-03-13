package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Genero;
import com.example.demo.repository.GeneroRepository;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> listarGeneros() {
        return generoRepository.findAll();
    }

    @Transactional
    public void crearGenero(Genero genero) {
        generoRepository.save(genero);
    }

    @Transactional
    public void actualizarGenero(Long generoId, String nuevoNombre) {
        Genero genero = generoRepository.findById(generoId)
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
        genero.setNombre(nuevoNombre);
        generoRepository.save(genero);
    }

    @Transactional
    public void eliminarGenero(Long generoId) {
        Genero genero = generoRepository.findById(generoId)
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));

        if (!genero.getLibros().isEmpty()) {
            throw new RuntimeException("No se puede eliminar un género que tiene libros asociados");
        }

        generoRepository.delete(genero);
    }
}
