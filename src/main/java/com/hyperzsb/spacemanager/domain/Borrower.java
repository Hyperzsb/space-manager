package com.hyperzsb.spacemanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "room_borrower")
public class Borrower {
    @Id
    private int id;

    @Column(name = "borrower_name")
    private String name;

    @Column(name = "borrower_academy")
    @OneToOne(targetEntity = Academy.class)
    private Academy academy;
}
