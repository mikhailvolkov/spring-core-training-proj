package com.epam.spring.hometask.service.impl;

import com.epam.spring.hometask.dao.EventDAO;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
/**
 * @author Volkov_Mikhail
 */
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDAO eventDAO;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDAO.getByName(name);
    }

    @Override
    public Event save(@Nonnull Event object) {
        return eventDAO.save(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDAO.remove(object);
    }

    @Nullable
    @Override
    public Event getById(@Nonnull Long id) {
        return eventDAO.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDAO.getAll();
    }
}
