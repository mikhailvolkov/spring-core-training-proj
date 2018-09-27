package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.dao.AuditoriumDAO;
import com.epam.spring.hometask.domain.Auditorium;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Volkov_Mikhail
 */
@Repository
public class AuditoriumDAOImpl implements AuditoriumDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addAuditorium(Auditorium auditorium) {
        sessionFactory.getCurrentSession().save(auditorium);
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new TreeSet<>(sessionFactory.getCurrentSession().createQuery("from Auditoriums").list());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Query events = sessionFactory.getCurrentSession().createQuery("from Auditorium where name = :name");
        events.setParameter("name", name);
        return (Auditorium) events.list().get(0);
    }
}
