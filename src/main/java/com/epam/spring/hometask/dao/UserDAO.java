package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.AbstractDomainObjectService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


/**
 * @author Volkov_Mikhail
 */
public interface UserDAO extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable User getUserByEmail(@Nonnull String email);
}
