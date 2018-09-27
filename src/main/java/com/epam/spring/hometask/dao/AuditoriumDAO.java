package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;


/**
 * @author Volkov_Mikhail
 */
public interface AuditoriumDAO {

    public void addAuditorium(Auditorium auditorium);
    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    public @Nonnull
    Set<Auditorium> getAll();
    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    public @Nullable
    Auditorium getByName(@Nonnull String name);

}
