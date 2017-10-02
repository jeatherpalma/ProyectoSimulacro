/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.controladores;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.repositorio.RepositorioCategoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WorkNest8
 */
@RestController
@RequestMapping("/categoria")
public class ControladorCategoria {
    
    private final RepositorioCategoria repositoriocategoria;
    public int contadorRegistros = 0;
    @Autowired
    public ControladorCategoria(RepositorioCategoria repositoriocategoria) {
        this.repositoriocategoria = repositoriocategoria;
    }
    
    
    
    
    
    @RequestMapping(method = RequestMethod.POST, path = "/insertar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> agregarElementoJSON(@RequestBody Categoria categoria){
        
        if(repositoriocategoria.findByNombre(categoria.getNombre())==null){
           repositoriocategoria.sp_i_categoria(categoria.getNombre(), categoria.getDescripcion());
           return new ResponseEntity<Categoria>(categoria,HttpStatus.OK); 
        }else{
           return new ResponseEntity<Categoria>(categoria, HttpStatus.NOT_MODIFIED);
        }
        
        
    }
    
    @GetMapping("/leer")
    public List<Categoria> listCategorias(){
       
   return  repositoriocategoria.seleccionacategorias();
      
}
    
}