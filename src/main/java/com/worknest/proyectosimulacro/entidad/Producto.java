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
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="c_barras", type=String.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="nombre_prod", type=String.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion_prod", type=String.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="cantidad", type=int.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="precio_compra", type=Float.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="precio_venta", type=Float.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="idcategoria", type=Long.class)
		}
)
public class Producto implements Serializable{

    /**
     * @return the c_barras
     */
    public String getCbarras() {
        return c_barras;
    }

    /**
     * @param c_barras the c_barras to set
     */
    public void setCbarras(String c_barras) {
        this.c_barras = c_barras;
    }

    /**
     * @return the nombre_prod
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre_prod the nombre_prod to set
     */
    public void setNombre(String nombre_prod) {
        this.nombre = nombre_prod;
    }

    /**
     * @return the descripcion_prod
     */
    public String getDescripcionprod() {
        return descripcion_prod;
    }

    /**
     * @param descripcion_prod the descripcion_prod to set
     */
    public void setDescripcionprod(String descripcion_prod) {
        this.descripcion_prod = descripcion_prod;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precio_compra
     */
    public float getPreciocompra() {
        return precio_compra;
    }

    /**
     * @param precio_compra the precio_compra to set
     */
    public void setPreciocompra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    /**
     * @return the precio_venta
     */
    public float getPrecioventa() {
        return precio_venta;
    }

    /**
     * @param precio_venta the precio_venta to set
     */
    public void setPrecioventa(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    /**
     * @return the id_categoria
     */
    public Long getIdcategoria() {
        return idcategoria;
    }

    /**
     * @param idcategoria the id_categoria to set
     */
    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }
    
       @Id
    @Column(name = "c_barras") 
    private String c_barras;
    @Column(name = "nombre_prod")
    private String nombre;
    @Column(name = "descripcion_prod")
    private String descripcion_prod;
    @Column(name= "cantidad")
    private int cantidad;
    @Column(name= "precio_compra")
    private float precio_compra;
    @Column(name= "precio_venta")
    private float precio_venta;
    @Column(name= "idcategoria")
    private Long idcategoria;
    
}
