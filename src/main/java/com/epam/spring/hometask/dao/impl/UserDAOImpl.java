package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.dao.UserDAO;
import com.epam.spring.hometask.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Volkov_Mikhail
 */
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        Query users = sessionFactory.getCurrentSession().createQuery("from Users where name = :name");
        users.setParameter("email", email);
        return (User) users.list().get(0);
    }

    @Override
    public User save(@Nonnull User object) {
        sessionFactory.getCurrentSession().save(object);
        return object;
    }

    @Override
    public void remove(@Nonnull User object) {
        Query events = sessionFactory.getCurrentSession().createQuery("delete from Users where id = :id");
        events.setParameter("id", object.getId());
        events.executeUpdate();
    }

    @Nullable
    @Override
    public User getById(@Nonnull Long id) {
        Query users = sessionFactory.getCurrentSession().createQuery("from Events where id= :id");
        users.setParameter("id", id);
        return (User) users.list().get(0);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from Users").list();
        return users.isEmpty() ? Collections.emptyList() : users;
    }
}
