package com.test.ejemploSpring.Dao;

import com.test.ejemploSpring.Models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.StandardCharsets;
import java.util.List;

public interface IUsuariosDao {

    List<User> GetUsers();

    User GetUser(long id);

    User AddUSer(User user);

    User EditUSer(long id, User user);

    boolean DeleteUser(long id);
}

@Repository
@Transactional
class UsuariosDaoImpl implements IUsuariosDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> GetUsers() {
        String sql = "FROM User";
        return entityManager.createQuery(sql).getResultList();
    }

    @Override
    public User GetUser(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User AddUSer(User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        user.Hash = argon2.hash(1, 1024, 2, user.Nombre.getBytes(StandardCharsets.UTF_8));
        return entityManager.merge(user);
    }

    @Override
    public User EditUSer(long id, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean DeleteUser(long id) {
        User existe = entityManager.find(User.class, id);
        if (existe != null) {
            entityManager.remove(existe);
            return true;
        }
        return false;
    }

}
