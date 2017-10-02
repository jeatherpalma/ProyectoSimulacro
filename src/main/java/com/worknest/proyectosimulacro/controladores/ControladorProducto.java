/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.controladores;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.entidad.Producto;
import com.worknest.proyectosimulacro.repositorio.RepositorioCategoria;
import com.worknest.proyectosimulacro.repositorio.RepositorioProducto;
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
@RequestMapping("/inicio")
public class ControladorProducto {
    
    private final RepositorioProducto repositorioproducto;
    public int contadorRegistros = 0;
    @Autowired
    public ControladorProducto(RepositorioProducto repositorioproducto) {
        this.repositorioproducto = repositorioproducto;
    }
    
    
    @RequestMapping(method = RequestMethod.POST, path = "/insertarproducto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> agregarElementoJSON(@RequestBody Producto producto){
        
        
       
        
        if(repositorioproducto.findByNombre(producto.getC_barras())==null){
           repositorioproducto.sp_i_producto(producto.getC_barras(),producto.getNombre_prod(),producto.getDescripcion_prod(),producto.getCantidad(), producto.getPrecio_compra(),producto.getPrecio_venta(),producto.getId_categoria());
           return new ResponseEntity<Producto>(producto,HttpStatus.OK); 
        }else{
           return new ResponseEntity<Producto>(producto, HttpStatus.NOT_MODIFIED);
        }
        
        
    }
}
