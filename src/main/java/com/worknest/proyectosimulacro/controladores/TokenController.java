/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.controladores;

import com.worknest.proyectosimulacro.entidad.Usuario;
import com.worknest.proyectosimulacro.recursos.UsuarioBusqueda;
import com.worknest.proyectosimulacro.recursos.UsuarioLogin;
import com.worknest.proyectosimulacro.repositorio.RepositorioUsuario;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class TokenController {
    
    private BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public TokenController(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }
    
    /*Método para insertar un nuevo usuario en la base de datos, se puso para pruebas*/
    @RequestMapping(method = RequestMethod.POST, path = "/registro")
    public String registro(@RequestBody Usuario usuario){
        String passwordencode = bcryptpasswordencoder.encode(usuario.getContrasena());
        usuario.setContrasena(passwordencode);
        repositorioUsuario.save(usuario);
        return "Usuario registrado";
    }

    
    
    /*Método de login el cual necesita un nombre y una contraseña para verificar si se
    encuentra en la base de datos y crear un Token (el método es String sólo para ver
    el Token que se genero)*/
    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLogin login)throws ServletException{
        /*String en donde se almacenará el Token*/
        String jwtToken = "";
        
       
        /*Variables del login nombre y contrasena que se recuperán de la petición*/
        String usuario = login.getUsuario();
        String contrasena = login.getContrasena();
        
        /*Se crea un nuevo usuario a través del método findByNombre, el cual realiza
        una consula en la base de datos para verificar si el usuario se ecnuentra en
        la base de datos*/
        Usuario usuario1 = repositorioUsuario.findByUsuario(usuario);
        
        /*Si el usuario es nulo queiere decir que no se encuentra el usuario*/
        if(usuario1 == null){
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        /*Si el usuario se encuentra se verifica la contraseña de la base de datos con,
        la que el usuario envió en la petición*/
        String pwd = usuario1.getContrasena();
        /*Si son diferentes el login es incorrecto*/
        if(!bcryptpasswordencoder.matches(contrasena, pwd)){
           // throw new ServletException("Login invalido verifique su password");
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        /*se crea el token utilizando algoritmo de encriptación HS256*/
        jwtToken = Jwts.builder().setSubject(usuario).claim("roles", "admin").signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        //Se regresa el Token que se genero
        return new ResponseEntity<String>(jwtToken,HttpStatus.OK);
        
     }
    
    
    @RequestMapping(method = RequestMethod.POST, path = "/buscar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsuarioBusqueda> buscarUsuario(@RequestBody UsuarioBusqueda usuarioBusqueda){
        
        System.out.println("Este es el usuario " + usuarioBusqueda.getUsuario());
        if(repositorioUsuario.findByUsuario(usuarioBusqueda.getUsuario())!=null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        
    }
}
