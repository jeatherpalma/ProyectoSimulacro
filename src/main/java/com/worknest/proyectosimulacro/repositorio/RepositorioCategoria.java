/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WorkNest8
 */
@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long>{
    
    
    
    
    Categoria findByNombre(String nombre);

    
    @Procedure
    void sp_i_categoria(String nombre, String descripcion);
    
    
    @Query(value = "SELECT * FROM categoria", nativeQuery = true)
    List<Categoria> seleccionacategorias();
      
}

