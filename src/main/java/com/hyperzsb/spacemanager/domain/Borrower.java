package com.hyperzsb.spacemanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "room_borrower")
public class Borrower {
    @Id
    private Integer id;

    @Column(name = "borrower_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "academy_id", referencedColumnName = "id")
    private Academy academy;

    public Borrower(Integer id, String name, Academy academy) {
        this.id = id;
        this.name = name;
        this.academy = academy;
    }

    public Borrower() {
        this.id = 0;
        this.name = "";
        this.academy = null;
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

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }
}
