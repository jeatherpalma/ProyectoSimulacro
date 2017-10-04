
package com.worknest.proyectosimulacro.controladores;

import com.worknest.proyectosimulacro.entidad.Usuario;
import com.worknest.proyectosimulacro.recursos.ContrasenaUsuario;
import com.worknest.proyectosimulacro.recursos.RespuestaUsuario;
import com.worknest.proyectosimulacro.recursos.UsuarioBusqueda;
import com.worknest.proyectosimulacro.recursos.UsuarioLogin;
import com.worknest.proyectosimulacro.repositorio.RepositorioUsuario;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class TokenController {
    //Objeto BCryptPasswordEncoder para encriptar la contraseña
    private BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
    //Inyeccion de dependencias
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    //Método constructor de la case TokenController
    public TokenController(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }
    
    /*Método para cambiar la contrasena por si la olvido*/
    @RequestMapping(method = RequestMethod.POST, path = "/cambio")
    public ResponseEntity<String> registro(@RequestBody ContrasenaUsuario contrasenaUsuario){
        try {
            //Se busca el usuario que el cliente mando y se asigna a usuarioCambioContrasena
            Usuario usuarioCambioContrasena = repositorioUsuario.findByUsuario(contrasenaUsuario.getUsuario());
            //If que verifica si el usuario se encuentra en la contrasena
            if(usuarioCambioContrasena!=null){
                String passwordencode = bcryptpasswordencoder.encode(contrasenaUsuario.getContrasena()); //Encriptamos la contrasena
                usuarioCambioContrasena.setContrasena(passwordencode);//Asiganos al usuario la nueva contraseña encriptada
                repositorioUsuario.save(usuarioCambioContrasena);//Actualizamos al usuario en la base de datos
                return new ResponseEntity<>(HttpStatus.OK);//Regresamos un estatus 200 de operación exitosa
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); //Si no se hayo el usuario regresamos el estatus 404 no encontrado
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //Si hay problemas de conexion regresamos erro 500 problemas con el servidor
        }
        
        
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
    
    //Método que busca la pregunta secreta del usuario
    @RequestMapping(method = RequestMethod.POST, path = "/buscar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> buscarUsuario(@RequestBody UsuarioBusqueda usuarioBusqueda){
      
        Usuario usuario = repositorioUsuario.findByUsuario(usuarioBusqueda.getUsuario());//Se busca el usuario que se tecleo para mandar pregunta
        //If que verifica el usuario
        if(usuario!=null){
            String pregunta = usuario.getPsecreta(); //Si existe el usuario se guarda la pregunta secreta
            return new ResponseEntity<String>(pregunta,HttpStatus.OK);//Regresamos la pregunta con estatus 200
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //Si no existe el usuario se manda esttaus 404
        }
       
    }
    
    //Método que verifica la respuesta a la pregunta secreta
    @RequestMapping(method = RequestMethod.POST, path = "/respuestasecreta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RespuestaUsuario> verificaRespuestaSecreta(@RequestBody RespuestaUsuario respuestaUsuario){
        //Obtenemos la respuesta que se recibio
        String respuestaSecreta = respuestaUsuario.getRsecreta();
        //Buscamos al usuario que mando la respuesta
        Usuario usuario = repositorioUsuario.findByUsuario(respuestaUsuario.getUsuario());
        //If que comprueba la respueta que mando el usuario con la que está almacenada
        if(respuestaSecreta.equalsIgnoreCase(usuario.getRsecreta())){
            return new ResponseEntity<>(HttpStatus.OK);//Si la respuesta es igual estatus 200
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);//Si la respuesta no es igual estatus 409
        }
    }
    
}
