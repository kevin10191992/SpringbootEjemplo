package com.test.ejemploSpring.Conttrollers;

import com.test.ejemploSpring.Models.CodigoDescripcionData;
import com.test.ejemploSpring.Models.User;
import com.test.ejemploSpring.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil _JwtUtil;

    @RequestMapping(value = "Auth", method = RequestMethod.POST)
    public CodigoDescripcionData GetToken(@RequestBody User user) {
        CodigoDescripcionData<String> result = new CodigoDescripcionData<String>();
        result.Codigo = "01";
        result.Descripicion = "Exito";
        if (user != null) {
            result.Datos = _JwtUtil.create(user.Nombre, user.Apellido);
        } else {
            result.Codigo = "02";
            result.Descripicion = "Error";
        }
        return result;
    }

    public boolean ValidateToken(@RequestHeader(value = "Authorization") String Token) {


        if (_JwtUtil.getKey(Token) != null) {
            return true;
        } else {
            return false;
        }

    }

}
