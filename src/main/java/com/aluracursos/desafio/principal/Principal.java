package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.Datos;
import com.aluracursos.desafio.model.DatosLibros;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private final String URL_API = "https://gutendex.com/books/";
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu(){
        var json = consumoAPI.obtenerDatos(URL_API);

        System.out.println(json);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);



        //top 10 libros mas descargados
        System.out.println("Top 10 libros mas descargados");
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDescargas).reversed())//reversed porque sorted ordena de menor a mayor
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);


        System.out.println("\nIngrese el nombre del libro que desea buscar");
        String busquedaUsuario = scanner.nextLine();
        json = consumoAPI.obtenerDatos(URL_API + "?search=" + busquedaUsuario.toLowerCase().replace(" ","%20"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> datosLibros = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(busquedaUsuario.toLowerCase()))
                .findFirst();
        if(datosLibros.isPresent()){
            System.out.println( "Libro encontrado: \n" + datosLibros.get());
        }else {
            System.out.println("Libro no encontrado");
        }






    }
}
