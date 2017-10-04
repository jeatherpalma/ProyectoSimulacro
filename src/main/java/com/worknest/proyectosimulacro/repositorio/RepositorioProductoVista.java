/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Producto;
import com.worknest.proyectosimulacro.entidad.ProductoInventario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * clase que contiene los procedimientos que se utilizan mediante JPA para 
 * gestionar a "Inventario de producto" desde una vista en la base de datos llamada "v_inventario", 
 * el cual se requiere para obtener el total de los productos, con su respectiva categoria, no el id 
 * de la misma, ya que es una id foranea
 */
public interface RepositorioProductoVista extends JpaRepository<ProductoInventario, String>{
    
    //metodo que se invoca para ejecutar el Query   
    @Query(value = "SELECT * FROM v_inventario", nativeQuery = true)
    List<ProductoInventario> seleccionaproductos();
    
    
}
