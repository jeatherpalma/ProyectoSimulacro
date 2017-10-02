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
    private String c_barras;
    @Column(name = "nombre_prod")
    private String nombre;
    @Column(name = "descripcion_prod")
    private String descripcion_prod;
    @Column(name= "cantidad")
    private Long cantidad;
    @Column(name= "precio_compra")
    private float precio_compra;
    @Column(name= "precio_venta")
    private float precio_venta;
    @Column(name= "idcategoria")
    private Long idcategoria;

    public String getC_barras() {
        return c_barras;
    }

    public void setC_barras(String c_barras) {
        this.c_barras = c_barras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion_prod() {
        return descripcion_prod;
    }

    public void setDescripcion_prod(String descripcion_prod) {
        this.descripcion_prod = descripcion_prod;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }
    
    
    
    
}
