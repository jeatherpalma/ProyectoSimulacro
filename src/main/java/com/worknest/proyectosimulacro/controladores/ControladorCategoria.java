
package com.worknest.proyectosimulacro.controladores;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.entidad.Producto;
import com.worknest.proyectosimulacro.repositorio.RepositorioCategoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *Esta clase contiene las solicituded necesarias para maniplar "Categorias"
 * por medio de los URI.
 */
@RestController
@RequestMapping("/categoria")
public class ControladorCategoria {
    /**
     * inyeccion de dependencias y codigo para el funciomamiento de la clase
     * en spring
     */
    
    private final RepositorioCategoria repositoriocategoria;
    
    @Autowired
    public ControladorCategoria(RepositorioCategoria repositoriocategoria) {
        this.repositoriocategoria = repositoriocategoria;
    }
    
    
    @RequestMapping(method = RequestMethod.POST, path = "/insertar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> agregarElementoJSON(@RequestBody Categoria categoria){
     /**
     * Declaraciones de spring para usar el método post en la URI "insertar" y formatear los datos a JSON que se recojen desde el cuerpo de html
     */
        
       /**
        * En esta condición, buscamos que si el nombre de la categoria no existe en el momento de agregar una categoria, llamamos al procedimiento almacenado en la base de datos "sp_i_categoria"
        * para insertar dicha categoria. Se recibe un JSON con los datos de la categoría, donde el campo "categoria" es un String con el nombre de la categoria.
        * Luego se busca la "id" de la categoria a partir del "nombre" de la categoria, en la entidad "Categoria".
        * Despues se devuelve hacia la base de datos los datos con el campo "categoria" de tipo Long con la "id" de la categoria, ya que es una id foranea
        * En caso contrario, se retorna el estado "NOT_MODIFIED"
        */
        if(repositoriocategoria.findByNombre(categoria.getNombre())==null){
           repositoriocategoria.sp_i_categoria(categoria.getNombre(), categoria.getDescripcion());
                      Categoria categoriaEncontrada = repositoriocategoria.findByNombre(categoria.getNombre());
                categoria.setId(categoriaEncontrada.getId());
           return new ResponseEntity<Categoria>(categoria,HttpStatus.OK); 
        }else{
           return new ResponseEntity<Categoria>(categoria, HttpStatus.NOT_MODIFIED);
        }   
    }
    
    @GetMapping("/leer")
    public List<Categoria> listCategorias(){
        /**
         * Con este método devolvemos todas las categorias
         */
       
   return  repositoriocategoria.seleccionacategorias();
      
}
     
     /*Método para modificar una categoria a la base de datos*/
    @RequestMapping(method = RequestMethod.PUT, path = "/modificar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String modificarElementoJSON(@RequestBody Categoria categoria){
       repositoriocategoria.save(categoria);
        return "categoria modificada";
    }
    /*Método para eliminar una categoria de la base de datos*/
    @RequestMapping(method = RequestMethod.DELETE, path = "/eliminar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String eliminarElemento(@RequestBody Categoria categoria){
        repositoriocategoria.sp_d_categoria(categoria.getId());
        return "Categoria borrada";        
    }
     
}