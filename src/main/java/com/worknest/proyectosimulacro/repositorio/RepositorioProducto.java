
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.entidad.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

/**
 *clase que contiene los procedimientos que se utilizan mediante jpa para 
 * gestionar a "productos"
 */
public interface RepositorioProducto extends JpaRepository<Producto, String> {
    
    
    Producto findByNombre(String C_barras);
    
    @Procedure
    void sp_i_producto(String codigoBarras, String nombre,String descripcion, Long cantidad,Float precioCompra, Float precioVenta , Long categoria);
    
    @Procedure
    String sp_q_inventario();
    
    @Query(value = "SELECT * FROM producto", nativeQuery = true)
    List<Producto> seleccionaproductos();
    
    
     
    
}
