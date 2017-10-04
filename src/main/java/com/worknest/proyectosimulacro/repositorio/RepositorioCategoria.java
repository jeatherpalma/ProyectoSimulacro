
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

/**
 *clase que contiene los procedimientos que se utilizan mediante jpa para 
 * gestionar a "categorias" así como la alta de los procedimientos almacenados 
 * requeridos que se invocan en la base de datos
 */

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long>{
     
    // metodos usados con JPA 
    Categoria findByNombre(String nombre);
    Categoria findById(Long id);
    
     //alta de procedimientos almacenados
    @Procedure
    void sp_i_categoria(String nombre, String descripcion);
    
    @Procedure
     void sp_d_categoria(Long id);
    
     //uso de SQL por medio de JPA, lo cual el resultado se almacena en una lista del tipo "Categoria"
    @Query(value = "SELECT * FROM categoria", nativeQuery = true)
    List<Categoria> seleccionacategorias();
      
}

