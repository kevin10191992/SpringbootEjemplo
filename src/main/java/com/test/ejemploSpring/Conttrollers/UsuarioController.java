package com.test.ejemploSpring.Conttrollers;

import com.test.ejemploSpring.Dao.IUsuariosDao;
import com.test.ejemploSpring.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuariosDao iUsuariosDao;

    @RequestMapping(value = "api/getusers", method = RequestMethod.GET)
    public List<User> GetUsers() {
        return iUsuariosDao.GetUsers();
    }

    @RequestMapping(value = "api/getuser/{id}", method = RequestMethod.GET)
    public User GetUser(@PathVariable long id) {
        return iUsuariosDao.GetUser(id);
    }

    @RequestMapping(value = "api/adduser", method = RequestMethod.POST)
    public User AddUSer(@RequestBody User user) {
        return iUsuariosDao.AddUSer(user);
    }

    @RequestMapping(value = "api/edituser/{id}", method = RequestMethod.PUT)
    public User EditUSer(@PathVariable long id, @RequestBody User mod) {
        return iUsuariosDao.EditUSer(id, mod);
    }

    @RequestMapping(value = "api/deleteuser/{id}", method = RequestMethod.DELETE)
    public boolean DeleteUser(@PathVariable long id) {
        return iUsuariosDao.DeleteUser(id);
    }
}
