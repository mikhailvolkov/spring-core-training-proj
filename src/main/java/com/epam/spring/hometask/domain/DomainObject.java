package com.epam.spring.hometask.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Volkov_Mikhail
 */
@MappedSuperclass
public class DomainObject {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
