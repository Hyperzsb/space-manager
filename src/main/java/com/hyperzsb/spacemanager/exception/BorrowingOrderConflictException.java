package com.hyperzsb.spacemanager.exception;

import com.google.gson.Gson;
import com.hyperzsb.spacemanager.domain.BorrowingOrder;

public class BorrowingOrderConflictException extends RuntimeException {
    private BorrowingOrder borrowingOrder;

    public BorrowingOrderConflictException(BorrowingOrder borrowingOrder) {
        super(new Gson().toJson(borrowingOrder));
        this.borrowingOrder = borrowingOrder;
    }

    public BorrowingOrder getBorrowingOrder() {
        return borrowingOrder;
    }

    public void setBorrowingOrder(BorrowingOrder borrowingOrder) {
        this.borrowingOrder = borrowingOrder;
    }
}
