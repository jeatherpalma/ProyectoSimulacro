package com.worknest.proyectosimulacro;

import com.worknest.proyectosimulacro.configuracion.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoSimulacroApplication {
        
    /*Se crea un Bean que contienen un filtro hacia las direcciones seleccionadas url que
         requerirán autenticación */
        
        @Bean
	public FilterRegistrationBean jwtFilter() {
            //Se crea un registro de autenticación
            final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
            //Se le agrega el filtro JWT esperando recibir token
            registrationBean.setFilter(new JwtFilter());
            //Agregamos las restricciones de los servicios esperando autenticación
            registrationBean.addUrlPatterns("/producto/*");
            registrationBean.addUrlPatterns("/categoria/*");
            registrationBean.addUrlPatterns("/venta/*");
            //Retornamos la registración del filtro con cada una de las direcciones
            //restringidas que sólo pueden ser accesadas por el usuario registrado
            return registrationBean;
        }
	public static void main(String[] args) {
		SpringApplication.run(ProyectoSimulacroApplication.class, args);
	}
}
