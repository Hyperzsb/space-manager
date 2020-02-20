package com.hyperzsb.spacemanager.borrowing.controller;

import com.hyperzsb.spacemanager.borrowing.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.borrowing.enumeration.OrderStatus;
import com.hyperzsb.spacemanager.borrowing.exception.BorrowingOrderConflictException;
import com.hyperzsb.spacemanager.borrowing.exception.BorrowingOrderDaoException;
import com.hyperzsb.spacemanager.borrowing.service.BorrowingOrderService;
import com.hyperzsb.spacemanager.borrowing.vo.BorrowingOrderVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

    private Logger logger = LogManager.getLogger(BorrowingController.class);

    @Autowired
    private BorrowingOrderService borrowingOrderService;

    @PostMapping("/")
    public ResponseEntity<BorrowingOrderVo> addBorrowingOrder(@RequestBody BorrowingOrderVo borrowingOrderVo) throws UnsupportedEncodingException {
        try {
            BorrowingOrder borrowingOrder = BorrowingOrderVo.convertToPo(borrowingOrderVo);
            borrowingOrder.setOrderStatus(OrderStatus.getOrderStatusByValue(2));
            borrowingOrder = borrowingOrderService.addOrder(borrowingOrder);
            borrowingOrderVo = BorrowingOrderVo.convertToVo(borrowingOrder);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            String msg = "Add new borrowing order succeeded.";
            httpHeaders.add("success", success);
            httpHeaders.add("message", msg);
            return new ResponseEntity<BorrowingOrderVo>(borrowingOrderVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            String success;
            String msg;
            if (e instanceof BorrowingOrderConflictException) {
                BorrowingOrderConflictException exception = (BorrowingOrderConflictException) e;
                success = "C";
                //msg = URLEncoder.encode(BorrowingOrderVo.convertToVo(exception.getBorrowingOrder()).toString(), "utf-8");
                msg = BorrowingOrderVo.convertToVo(exception.getBorrowingOrder()).toString();
            } else if (e instanceof BorrowingOrderDaoException) {
                success = "N";
                msg = "Database error.";
            } else {
                success = "U";
                msg = "Unexpected server internal error.";
            }
            httpHeaders.add("success", success);
            httpHeaders.add("message", msg);
            return new ResponseEntity<BorrowingOrderVo>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<BorrowingOrderVo> getOrder() {
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrder();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingOrderVo> getOrderById(@PathVariable("id") Integer id) {
        try {
            BorrowingOrderVo borrowingOrderVo = BorrowingOrderVo.convertToVo(borrowingOrderService.getOrderByOrderId(id));
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            String msg = "Add new borrowing order succeeded.";
            httpHeaders.add("success", success);
            httpHeaders.add("message", msg);
            return new ResponseEntity<BorrowingOrderVo>(borrowingOrderVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            String success;
            String msg;
            if (e instanceof BorrowingOrderDaoException) {
                success = "N";
                msg = "Database error.";
            } else {
                success = "U";
                msg = "Unexpected server internal error.";
            }
            httpHeaders.add("success", success);
            httpHeaders.add("message", msg);
            return new ResponseEntity<BorrowingOrderVo>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/borrower/id/{id}")
    public List<BorrowingOrderVo> getOrderByBorrowerId(@PathVariable("id") Integer id) {
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrderByBorrowerId(id);
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }

    @GetMapping("/borrower/name/{name}")
    public List<BorrowingOrderVo> getOrderByBorrowerName(@PathVariable("name") String name) throws UnsupportedEncodingException {
        name = URLDecoder.decode(name, "utf-8");
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrderByBorrowerName(name);
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }

    @GetMapping("/room/id/{id}")
    public List<BorrowingOrderVo> getOrderByRoomId(@PathVariable("id") Integer id) {
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrderByRoomId(id);
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }

    @GetMapping("/room/name/{name}")
    public List<BorrowingOrderVo> getOrderByRoomName(@PathVariable("name") String name) throws UnsupportedEncodingException {
        name = URLDecoder.decode(name, "utf-8");
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrderByRoomName(name);
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }

}