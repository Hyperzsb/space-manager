package com.hyperzsb.spacemanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "borrowing_order")
public class BorrowingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private Borrower borrower;
    
}
