package com.hyperzsb.spacemanager.controller;

import com.hyperzsb.spacemanager.domain.BorrowingOrder;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class BorrowingOrderController {
    private Logger logger = LogManager.getLogger(BorrowingOrderController.class);
    @Autowired
    private BorrowingOrderService borrowingOrderService;

    @PostMapping("/")
    public ResponseEntity<BorrowingOrderVo> addBorrowingOrder(@RequestBody BorrowingOrderVo borrowingOrderVo) {
        try {
            BorrowingOrder borrowingOrder = BorrowingOrderVo.convertToPo(borrowingOrderVo);
            borrowingOrder = borrowingOrderService.addOrder(borrowingOrder);
            borrowingOrderVo = BorrowingOrderVo.convertToVo(borrowingOrder);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            httpHeaders.add("success", success);
            return new ResponseEntity<BorrowingOrderVo>(borrowingOrderVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "N";
            httpHeaders.add("success", success);
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
            httpHeaders.add("success", success);
            return new ResponseEntity<BorrowingOrderVo>(borrowingOrderVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            String success;
            if (e instanceof BorrowingOrderDaoException) {
                success = "N";
            } else {
                success = "U";
            }
            httpHeaders.add("success", success);
            return new ResponseEntity<BorrowingOrderVo>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/borrowerId/{id}")
    public List<BorrowingOrderVo> getOrderByBorrowerId(@PathVariable("id") Integer id) {
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrderByBorrowerId(id);
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }

    @GetMapping("/borrowerName/{name}")
    public List<BorrowingOrderVo> getOrderByBorrowerName(@PathVariable("name") String name) throws UnsupportedEncodingException {
        name = URLDecoder.decode(name, "utf-8");
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrderByBorrowerName(name);
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }

    @GetMapping("/roomId/{id}")
    public List<BorrowingOrderVo> getOrderByRoomId(@PathVariable("id") Integer id) {
        logger.info("!!!");
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrderByRoomId(id);
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }

    @GetMapping("/roomName/{name}")
    public List<BorrowingOrderVo> getOrderByRoomName(@PathVariable("name") String name) throws UnsupportedEncodingException {
        logger.info("???");
        name = URLDecoder.decode(name, "utf-8");
        List<BorrowingOrder> borrowingOrderList = borrowingOrderService.getOrderByRoomName(name);
        List<BorrowingOrderVo> borrowingOrderVoList = new ArrayList<BorrowingOrderVo>();
        for (BorrowingOrder borrowingOrder : borrowingOrderList) {
            logger.info(borrowingOrder.toString());
            borrowingOrderVoList.add(BorrowingOrderVo.convertToVo(borrowingOrder));
        }
        return borrowingOrderVoList;
    }
}
