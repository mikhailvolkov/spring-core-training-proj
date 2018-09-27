package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.dao.EventDAO;
import com.epam.spring.hometask.domain.Event;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.InjectService;
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
public class EventDAOImpl implements EventDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        Query events = sessionFactory.getCurrentSession().createQuery("from Events where name = :name");
        events.setParameter("name", name);
        return (Event) events.list().get(0);
    }

    @Override
    public Event save(@Nonnull Event object) {
        sessionFactory.getCurrentSession().save(object);
        return object;
    }

    @Override
    public void remove(@Nonnull Event object) {
        Query events = sessionFactory.getCurrentSession().createQuery("delete from Events where id = :id");
        events.setParameter("id", object.getId());
        events.executeUpdate();
    }

    @Nullable
    @Override
    public Event getById(@Nonnull Long id) {
        Query events = sessionFactory.getCurrentSession().createQuery("from Events where id= :id");
        events.setParameter("id", id);
        return (Event) events.list().get(0);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        List<Event> events = sessionFactory.getCurrentSession().createQuery("from Events").list();
        return events.isEmpty() ? Collections.emptyList() : events;
    }
}
