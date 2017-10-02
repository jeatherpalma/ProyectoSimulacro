/**
 * En Esta clase, se dan de alta las solicitudes que se usarán como URI de cada 
 * accion que se desea realizar. 
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
 *Esta clase contiene las solicituded necesarias para maniplar "productos"
 * por medio de los URI.
 */
@RestController 

@RequestMapping("/producto") 

public class ControladorProducto {
   /**
     * inyeccion de dependencias y codigo para el funciomamiento de la clase
     * en spring
     */
    private final RepositorioProducto repositorioproducto;
    @Autowired
    public ControladorProducto(RepositorioProducto repositorioproducto) {
      
        this.repositorioproducto = repositorioproducto;
        
    }
   
    @RequestMapping(method = RequestMethod.POST, path = "/insertar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> agregarElementoJSON(@RequestBody Producto producto){
     /**
     * Declaraciones de spring para usar el método post en la URI "insertar" y formatear los datos a JSON que se recojen desde el cuerpo de html
     */
     
     /**
        * En esta condicion, buscamos que si el nombre del producto no existe en el momento de agregar un producto, llamamos al procedimiento almacenado en la base de datos
        * para insertar dicho producto. En caso contrario, se retorna el estado "NOT_MODIFIED"
        */
        if(repositorioproducto.findByNombre(producto.getCodigoBarras())==null){
           repositorioproducto.sp_i_producto(producto.getCodigoBarras(),producto.getNombre(),producto.getDescripcion(),producto.getCantidad(), producto.getPrecioCompra(),producto.getPrecioVenta(),producto.getCategoria());
           return new ResponseEntity<Producto>(producto,HttpStatus.OK); 
        }else{
           return new ResponseEntity<Producto>(producto, HttpStatus.NOT_MODIFIED);
        }
        
        
    }
    
    @GetMapping("/leer")
    public String listCategorias(){
       /**
         * Con este método devolvemos todos los productos
         */
   return  repositorioproducto.sp_q_inventario();
}
}