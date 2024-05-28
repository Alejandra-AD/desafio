package com.aluracursos.desafio.model;

import java.util.List;

public record DatosLibros(
        String titulo,
        List <DatosAutor> autor,
        List <String> idiomas,
        Double numeroDescargas

) {
}
