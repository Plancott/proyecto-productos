package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                "El usuario: " + id + " no existe"));
    }

    @Override
    public User save(User user) {
        user.setActive(Boolean.TRUE);
        user.setFechaRegistro(new Date());
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "El usuario: " + user.getId() + " no existe");
        }
        return userRepository.save(user);
    }

    @Override
    public User patch(Long id, User userData) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "El usuario: " + id + " no existe"));

        if (userData.getName() != null) {
            user.setName(userData.getName());
        }
        if (userData.getEmail() != null) {
            user.setEmail(userData.getEmail());
        }
        if (userData.getActive() != null) {
            user.setActive(userData.getActive());
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        user.setActive(Boolean.FALSE);
        userRepository.save(user);
    }
}
