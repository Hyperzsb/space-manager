package com.hyperzsb.spacemanager.controller;

import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.service.BorrowingOrderService;
import com.hyperzsb.spacemanager.vo.BorrowingOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrow")
public class BorrowingOrderController {
    @Autowired
    private BorrowingOrderService borrowingOrderService;

    @PostMapping("/")
    public BorrowingOrder addBorrowingOrder(@RequestBody BorrowingOrderVo borrowingOrderVo) {
        return null;
    }
}
