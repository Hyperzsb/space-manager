package com.hyperzsb.spacemanager.controller;

import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.emuneration.OrderStatus;
import com.hyperzsb.spacemanager.exception.BorrowingOrderConflictException;
import com.hyperzsb.spacemanager.exception.BorrowingOrderDaoException;
import com.hyperzsb.spacemanager.service.BorrowingOrderService;
import com.hyperzsb.spacemanager.vo.BorrowingOrderVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class BorrowingOrderController {
    private Logger logger = LogManager.getLogger(BorrowingOrderController.class);
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
//                msg = URLEncoder.encode(BorrowingOrderVo.convertToVo(exception.getBorrowingOrder()).toString(), "utf-8");
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

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingOrderVo> updateOrderById(@PathVariable("id") Integer id, @RequestBody BorrowingOrderVo borrowingOrderVo) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            String msg = "Update borrowing order succeeded.";
            BorrowingOrder borrowingOrder = BorrowingOrderVo.convertToPo(borrowingOrderVo);
            borrowingOrder.setId(id);
            borrowingOrderVo = BorrowingOrderVo.convertToVo(borrowingOrderService.updateOrderByOrderId(id, borrowingOrder));
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

    @PutMapping("/admin/{id}/{status}")
    public ResponseEntity<Boolean> changeOrderStatus(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            String msg = "Update borrowing order succeeded.";
            BorrowingOrder borrowingOrder = borrowingOrderService.getOrderByOrderId(id);
            borrowingOrder.setOrderStatus(OrderStatus.getOrderStatusByValue(status));
            borrowingOrderService.updateOrderByOrderId(id, borrowingOrder);
            httpHeaders.add("success", success);
            httpHeaders.add("message", msg);
            return new ResponseEntity<Boolean>(true, httpHeaders, HttpStatus.CREATED);
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
            return new ResponseEntity<Boolean>(false, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
