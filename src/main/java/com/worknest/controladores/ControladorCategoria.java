/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.controladores;

import com.worknest.entidad.Categoria;
import com.worknest.repositorio.RepositorioCategoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WorkNest8
 */
@RestController
@RequestMapping("/test")
public class ControladorCategoria {
    
    private final RepositorioCategoria repositoriocategoria;
    public int contadorRegistros = 0;
    @Autowired
    public ControladorCategoria(RepositorioCategoria repositoriocategoria) {
        this.repositoriocategoria = repositoriocategoria;
    }
    @GetMapping
    public List<Categoria> listCategoria(){
        
        return repositoriocategoria.seleccionaTodasLasCategoria();
    }
    
    
    
    
    @RequestMapping(method = RequestMethod.POST, path = "/insertarcategoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String agregarElementoJSON(@RequestBody Categoria persona){
      
        repositoriocategoria.insertarcategoria(persona.getNombre(), persona.getDescripcion());
        return "agregado ";
    }


}
