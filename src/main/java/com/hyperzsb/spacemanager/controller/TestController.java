package com.hyperzsb.spacemanager.controller;

import com.hyperzsb.spacemanager.domain.Borrower;
import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.repository.AcademyRepository;
import com.hyperzsb.spacemanager.repository.BorrowerRepository;
import com.hyperzsb.spacemanager.repository.BorrowingOrderRepository;
import com.hyperzsb.spacemanager.repository.RoomRepository;
import com.hyperzsb.spacemanager.service.BorrowingOrderService;
import com.hyperzsb.spacemanager.service.RoomService;
import com.hyperzsb.spacemanager.vo.BorrowingOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private AcademyRepository academyRepository;
    @Autowired
    private BorrowerRepository borrowerRepository;
    @Autowired
    private BorrowingOrderRepository borrowingOrderRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BorrowingOrderService borrowingOrderService;
    @Autowired
    private RoomService roomService;

    @PostMapping("/order")
    @ResponseBody
    public BorrowingOrder addOrder(@RequestBody BorrowingOrderVo borrowingOrderVo) {
        try {
            BorrowingOrder borrowingOrder = BorrowingOrderVo.convertToPo(borrowingOrderVo);
            return borrowingOrderService.addOrder(borrowingOrder);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/order/{id}")
    @ResponseBody
    public BorrowingOrder getOrderById(@PathVariable("id") Integer id) {
        try {
            return borrowingOrderService.getOrderByOrderId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/borrower/{id}")
    @ResponseBody
    public Borrower getBorrowerById(@PathVariable("id") Integer id) {
        return borrowerRepository.findById(id).get();
    }
}
