/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.entidad.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

/**
 *
 * @author WorkNest8
 */
public interface RepositorioProducto extends JpaRepository<Producto, String> {
    
    
    Producto findByNombre(String C_barras);
    
    @Procedure
    void sp_i_producto(String codigoBarras, String nombre,String descripcion, Long cantidad,Float precioCompra, Float precioVenta , Long categoria);
    
     @Query(value = "SELECT * FROM producto", nativeQuery = true)
    List<Producto> seleccionaproductos();
    
    
     
    
}
