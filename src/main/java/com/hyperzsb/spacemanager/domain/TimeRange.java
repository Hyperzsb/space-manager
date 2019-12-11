package com.hyperzsb.spacemanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrow_time_range")
public class TimeRange {
    @Id
    private int id;
    @Column(name = "start_time")
    private int startTime;
    @Column(name = "end_time")
    private int endTime;
}
