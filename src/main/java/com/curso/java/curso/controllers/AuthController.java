package com.curso.java.curso.controllers;

import com.curso.java.curso.dao.UsuarioDao;
import com.curso.java.curso.models.Usuario;
import com.curso.java.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioLogeado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLogeado != null){

            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());

            return tokenJwt;
        }
        return "FAIL";
    }
}
