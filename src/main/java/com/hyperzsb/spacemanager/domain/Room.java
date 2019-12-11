package com.hyperzsb.spacemanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "space_room")
public class Room {
    @Id
    private int id;
    @Column(name = "room_name")
    private String name;
    @Column(name = "room_description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
