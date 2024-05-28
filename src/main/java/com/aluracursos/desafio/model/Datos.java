package com.aluracursos.desafio.model;


import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record Datos(
        @JsonAlias("results") List<DatosLibros> resultados
) {

}
