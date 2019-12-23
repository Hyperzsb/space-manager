package com.hyperzsb.spacemanager.exception;

import com.google.gson.Gson;
import com.hyperzsb.spacemanager.domain.BorrowingOrder;

public class BorrowingOrderConflictException extends RuntimeException {
    public BorrowingOrderConflictException(BorrowingOrder borrowingOrder) {
        super(new Gson().toJson(borrowingOrder));
    }
}
