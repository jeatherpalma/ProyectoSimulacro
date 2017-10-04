/**
 * En Esta clase, se dan de alta las solicitudes que se usarán como URI de cada 
 * accion que se desea realizar. 
 */
package com.worknest.proyectosimulacro.controladores;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.recursos.CodigoBarras;
import com.worknest.proyectosimulacro.entidad.Producto;
import com.worknest.proyectosimulacro.entidad.ProductoInventario;
import com.worknest.proyectosimulacro.repositorio.RepositorioCategoria;
import com.worknest.proyectosimulacro.repositorio.RepositorioProducto;
import com.worknest.proyectosimulacro.repositorio.RepositorioProductoVista;
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
    @Autowired
    private RepositorioProducto repositorioproducto;
    @Autowired
    private RepositorioProductoVista reposirotioVistaProductos;
    @Autowired
    private RepositorioCategoria repositoriocategoria;
    
 
   
   
    @RequestMapping(method = RequestMethod.POST, path = "/insertar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoInventario> agregarElementoJSON(@RequestBody ProductoInventario producto){
     /**
     * Declaraciones de spring para usar el método post en la URI "insertar" y formatear los datos a JSON que se recojen desde el cuerpo de html
     */
     
     /**
        * En esta condición, buscamos que si el codigo de barra de un productgo no existe en el momento de agregar un nuevo producto, llamamos al procedimiento almacenado "sp_i_producto" 
        * en la base de datos para insertar dicha producto: Se recibe un JSON con los datos del producto, donde el campo "categoria" es un String con el nombre de la categoria a la que pertenece.
        * Luego se busca la "id" de la categoria a partir del "nombre" de la categoria, en la entidad "Categoria".
        * Despues se devuelve hacia la base de datos los datos con el campo "categoria" de tipo Long con la "id" de la categoria, ya que es una id foranea
        * En caso contrario, se retorna el estado "NOT_MODIFIED"
        * 
        */
        if(repositorioproducto.findByCodigoBarras(producto.getCodigoBarras())==null){
            
           Categoria categoriaEncontrada = repositoriocategoria.findByNombre(producto.getCategoria());
           repositorioproducto.sp_i_producto(producto.getCodigoBarras(),producto.getNombre(),producto.getDescripcion(),producto.getCantidad(), producto.getPrecioCompra(),producto.getPrecioVenta(),categoriaEncontrada.getId());
           return new ResponseEntity<ProductoInventario>(producto,HttpStatus.OK); 
           
        }else{
           return new ResponseEntity<ProductoInventario>(producto, HttpStatus.NOT_MODIFIED);
        }
    }
    
    @GetMapping("/leer")
    public List<ProductoInventario> listCategorias(){
       /**
         * Con este método devolvemos todos los productos
         */
   return  reposirotioVistaProductos.seleccionaproductos();
}
    
    /*Método que permite modificar los datos de un producto*/
      @RequestMapping(method = RequestMethod.PUT, path = "/modificar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String modificarElementoJSON(@RequestBody Producto producto){
                
       repositorioproducto.save(producto);
        return "categoria modificada";
    }
    /*Método que elimina un producto de la base de datos*/
    @RequestMapping(method = RequestMethod.PUT, path = "/eliminar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String eliminarElemento(@RequestBody Producto producto){
       repositorioproducto.delete(producto.getCodigoBarras());
        return "Categoria borrada";        
    }

    
    /**
     * Obtenemos la cantidad de productos de la base de datos
     */
    @RequestMapping(method = RequestMethod.POST, path = "/buscar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> buscarProductoCarrito(@RequestBody CodigoBarras c_barras){       
        
        
        Producto productoBuscarCarrito = repositorioproducto.findByCodigoBarras(c_barras.getC_barras());//Se busca el producto en base al codigo de barras
        /**
         * Ciclo if que verifica la existencia del producto con el codigo de barras en la base de datos
         */
        if(productoBuscarCarrito != null){
            //Si se haya el producto se regresa el estatus 200
            return new ResponseEntity<Producto>(productoBuscarCarrito, HttpStatus.OK);       
        }else{
            //Si no se haya el producto se regresa el estatus 404
            return new ResponseEntity<Producto>(productoBuscarCarrito, HttpStatus.NOT_FOUND);       
        }
        
    }
}