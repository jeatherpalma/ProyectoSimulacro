/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

/**
 *
 * @author WorkNest8
 */
public interface RepositorioProducto extends JpaRepository<Producto, Long> {
    
    
    Categoria findByNombre(String c_barras);
    
    @Procedure
    void sp_i_producto(String c_barras, String nombre_prod,String descripcion_prod, int cantidad,float precio_compra, float precio_venta , Long idcategoria);
}
