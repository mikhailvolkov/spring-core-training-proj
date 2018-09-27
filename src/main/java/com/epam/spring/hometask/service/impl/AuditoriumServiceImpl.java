package com.epam.spring.hometask.service.impl;

import com.epam.spring.hometask.dao.AuditoriumDAO;
import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * @author Volkov_Mikhail
 */
@Service
public class AuditoriumServiceImpl implements AuditoriumService {
    @Autowired
    private AuditoriumDAO auditoriumDAO;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumDAO.getAll();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDAO.getByName(name);
    }
}
