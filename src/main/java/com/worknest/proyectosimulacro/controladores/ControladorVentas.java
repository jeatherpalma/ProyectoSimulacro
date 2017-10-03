/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.controladores;

import com.worknest.proyectosimulacro.entidad.Historial;
import com.worknest.proyectosimulacro.entidad.Producto;
import com.worknest.proyectosimulacro.entidad.ProductoVenta;
import com.worknest.proyectosimulacro.entidad.Ventas;
import com.worknest.proyectosimulacro.repositorio.RepositorioHistorial;
import com.worknest.proyectosimulacro.repositorio.RepositorioProducto;
import com.worknest.proyectosimulacro.repositorio.RepositorioVentas;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WorkNest
 */
@RestController 
@RequestMapping("/venta")
public class ControladorVentas {
    /**
     * Inyeccion de dependencias
     */
    @Autowired
    private RepositorioVentas repositorioVentas;
    @Autowired
    private RepositorioProducto repositorioproducto;
    @Autowired
    private RepositorioHistorial repositorioHistorial;
    
    /**
     * Constructor de la clase ControladorVentas
     * @param repositorioVentas 
     */
    @Autowired
    public ControladorVentas(RepositorioVentas repositorioVentas) {
        this.repositorioVentas = repositorioVentas;
    }
    
    /**
     * Método que registra la venta
     * @param producto el cual es una lista de los productos seleccionados para una compra
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST, path = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ventas> agregarVenta(@RequestBody List<ProductoVenta> producto){
        
       /**
        * 
        * variable para sumar el total ;D
        */
        float sumaDeCompra = 0 , sumaDeVenta = 0 , ganancia = 0;
        
        /**
         * Ciclo que recorre la lista de productos seleccionados para una compra
         */
        for(ProductoVenta productoVenta : producto){

            Producto productoABuscar = repositorioproducto.findByCodigoBarras(productoVenta.getC_barras()); //Se busca el producto en base al codigo de barras
            sumaDeCompra += productoABuscar.getPrecioCompra() * productoVenta.getCantidad(); //Se calcula la suma del precio de compra de todos los productos
            sumaDeVenta += productoABuscar.getPrecioVenta() * productoVenta.getCantidad(); //Se calcula la suma del precio de venta de todos los productos
            
        }
        //Se calcula la ganancia neta de la venta
        ganancia = sumaDeVenta - sumaDeCompra;
        
        //Se crea la venta 
        Ventas venta = new Ventas(new Date(),sumaDeVenta,ganancia);
        //Se registra la venta en la base de datos
        repositorioVentas.save(venta);
        //Se restaura el valor de sumaVenta para asignarla a cada producto
        sumaDeVenta =0;
        /**
         * Ciclo que guarda los productos comprados en el historial
         */
        for (ProductoVenta productoVenta : producto) {
            
            Producto productoABuscar = repositorioproducto.findByCodigoBarras(productoVenta.getC_barras()); //Se busca el producto en base al codigo de barras
            sumaDeVenta = productoABuscar.getPrecioVenta() * productoVenta.getCantidad(); //Se calcula el total de la venta de un producto
            Historial historial = new Historial(venta.getIdventa(), productoVenta.getC_barras(), productoABuscar.getPrecioVenta(), productoVenta.getCantidad(), sumaDeVenta); //Se crea un nuevo historial del producto comprado
            repositorioHistorial.save(historial); //Se guarda el historial en la base de datos
            productoABuscar.setCantidad(productoABuscar.getCantidad()-productoVenta.getCantidad()); //Se resta la cantidad de productos comprados
            repositorioproducto.save(productoABuscar);//Se actualiza la base datos con los productos restantes
        }
       
        return new ResponseEntity<>(HttpStatus.OK); 

    } 
}
