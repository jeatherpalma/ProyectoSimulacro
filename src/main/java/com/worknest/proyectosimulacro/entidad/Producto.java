/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

/**
 *
 * @author WorkNest8
 */

@Entity
@Table(name = "producto")
@NamedStoredProcedureQuery(
		name = "sp_i_producto", 
		procedureName = "sp_i_producto", 
		parameters = {
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="barcode", type=String.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="name", type=String.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="description", type=String.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="cant", type=Long.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="pcompra", type=Float.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="pventa", type=Float.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="idcategoria", type=Long.class)
		}
)
public class Producto implements Serializable{

    @Id
    @Column(name = "c_barras") 
    private String codigoBarras;
    @Column(name = "nombre_prod")
    private String nombre;
    @Column(name = "descripcion_prod")
    private String descripcion;
    @Column(name= "cantidad")
    private Long cantidad;
    @Column(name= "precio_compra")
    private float precioCompra;
    @Column(name= "precio_venta")
    private float precioVenta;
    @Column(name= "idcategoria")
    private Long categoria;

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    
    
    
    
    
    
}
