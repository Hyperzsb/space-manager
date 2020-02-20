package com.hyperzsb.spacemanager.borrowing.controller;

import com.hyperzsb.spacemanager.borrowing.domain.BorrowingOrder;
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

@RestController
@RequestMapping("/manager/borrowing")
public class BorrowingManagerController {

    private Logger logger = LogManager.getLogger(BorrowingController.class);

    @Autowired
    private BorrowingOrderService borrowingOrderService;

    private ResponseEntity<BorrowingOrderVo> exceptionHandler(Exception e) {
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

    @PutMapping("/id/{id}")
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
            return exceptionHandler(e);
        }
    }

    @PutMapping("/id/{id}/orderStatusValue/{orderStatusValue}")
    public ResponseEntity<BorrowingOrderVo> updateOrderStatusById(@PathVariable("id") Integer id,
                                                                  @PathVariable("orderStatusValue") Integer orderStatusVale) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            String msg = "Update borrowing order succeeded.";
            BorrowingOrder borrowingOrder = borrowingOrderService.updateOrderStatusByOrderId(id, orderStatusVale);
            BorrowingOrderVo borrowingOrderVo = BorrowingOrderVo.convertToVo(borrowingOrder);
            httpHeaders.add("success", success);
            httpHeaders.add("message", msg);
            return new ResponseEntity<BorrowingOrderVo>(borrowingOrderVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
    }

}
