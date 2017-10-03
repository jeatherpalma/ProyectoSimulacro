/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jeather
 */
public interface RepositorioVentas extends JpaRepository<Ventas, Long>{
    
    
}