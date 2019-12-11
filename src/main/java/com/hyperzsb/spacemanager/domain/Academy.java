package com.hyperzsb.spacemanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "academy")
public class Academy {
    @Id
    private int id;
    @Column(name = "academy_name")
    private String name;
}
