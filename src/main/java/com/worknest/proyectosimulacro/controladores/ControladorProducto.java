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
 * @author WorkNest8
 */
/**
 *
 */
@RestController 
/**
 * bajo la programacion de "anotaciones", springboot usa la anotacion @RestController
 * para indicar que esta clase de java será inyectadas como servicios REST
 * y contendrá las operaciones que se soliciten, para su control, es a lo que llamamos
 * "inyeción de dependencias"(en este caso, las 
 * solicitudes necesarias para manipular "productos"
 * @author WorkNest8
 */
@RequestMapping("/producto") 
/**
 * con la anotacion @RequestMapping indicamos el nombre del recurso que se decea ejecutar, 
 * a partir de la URI inicial ("/inicio) y construimos la clase para contener las instrucciones para ejecutar
 */

public class ControladorProducto {
    /**
     * Esta clase prepara las operaciones que contienen la clase, para su uso como servicio REST
     */
    private final RepositorioProducto repositorioproducto;
    /**
     * @param repositorioproducto 
     * inyectamos la clase "Repositorio" para que springboot sea quien la invoque para su implementación
     * a partir de las etiquetas que la van definiendo (en este caso @RestRestController
     * y @RequestMapping) y contenga sus metodos para su ejecucion. 
     */
    @Autowired
    /**
     * Con la anotacion @Autowired, springboot busca en el proyecto, los metodos de 
     * de la clase "RepositorioProducto" para inyectarlos cuando sprinboot los mande a llamar en 
     * el método "ControladorProducto"
     */
    public ControladorProducto(RepositorioProducto repositorioproducto) {
        /**
         * inyectamos la clase "RepositorioProducto" para su uso en esta clase como 
         * un parámetro mas de la clase (inyeccion de código)
         */
            
        this.repositorioproducto = repositorioproducto;
        /**
         * 
         */
    }
   
    @RequestMapping(method = RequestMethod.POST, path = "/insertar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> agregarElementoJSON(@RequestBody Producto producto){
     /**
      * Con la anotación @RequestMapping declaramos el método http a usar (en este caso "POST") al momento de invocar la URI "/insertarproducto"
      * para insertar productos, los cuales se formatearán en JSON. Luego declaramos el metodo "agregarElementoJSON" al cual se le inyecta lo anterior.
      * Este métodouna lista que contendrá los objetos "Produccto"
      * que reciba, del mismo tipo "Producto", para su uso dentro de java. Al momento de invocar este método, recibe 
      * 
      */   
        
       
        
        if(repositorioproducto.findByNombre(producto.getCodigoBarras())==null){
           repositorioproducto.sp_i_producto(producto.getCodigoBarras(),producto.getNombre(),producto.getDescripcion(),producto.getCantidad(), producto.getPrecioCompra(),producto.getPrecioVenta(),producto.getCategoria());
           return new ResponseEntity<Producto>(producto,HttpStatus.OK); 
        }else{
           return new ResponseEntity<Producto>(producto, HttpStatus.NOT_MODIFIED);
        }
        
        
    }
    
    @GetMapping("/leer")
    public List<Producto> listCategorias(){
       
   return  repositorioproducto.seleccionaproductos();
}
}