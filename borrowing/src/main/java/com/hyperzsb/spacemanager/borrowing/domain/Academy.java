package com.hyperzsb.spacemanager.borrowing.domain;

import javax.persistence.*;

@Entity
@Table(name = "academy")
public class Academy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "academy_name")
    private String name;

    public Academy(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Academy(String name) {
        this.name = name;
    }

    public Academy() {
        this.id = 0;
        this.name = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toCustomString() {
        return "Academy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}