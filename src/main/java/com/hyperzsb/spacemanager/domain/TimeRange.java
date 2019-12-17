package com.hyperzsb.spacemanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "time_range")
public class TimeRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "end_time")
    private int endTime;
}
