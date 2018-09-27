package com.epam.spring.hometask.service.impl;

import com.epam.spring.hometask.dao.UserDAO;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author Volkov_Mikhail
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public User save(@Nonnull User object) {
        return userDAO.save(object);
    }

    @Override
    public void remove(@Nonnull User object) {
        userDAO.remove(object);
    }

    @Nullable
    @Override
    public User getById(@Nonnull Long id) {
        return userDAO.getById(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();
    }
}
