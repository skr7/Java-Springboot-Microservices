package com.tskr.payment.controller;

import com.tskr.payment.payload.request.PaymentRequest;
import com.tskr.payment.payload.response.PaymentResponse;
import com.tskr.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {


    private final PaymentService service;


    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody  PaymentRequest paymentRequest){

        log.info("PaymentController | doPayment is called");

        log.info("PaymentController | doPayment | paymentRequest : " + paymentRequest.toString());

        return new ResponseEntity<>(service.doPayment(paymentRequest), HttpStatus.OK);

    }

    @GetMapping("/order/{id}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsbyOrderID(@PathVariable("id") long orderId){

        log.info("PaymentController | doPayment is called");

        log.info("PaymentController | doPayment | orderId : " + orderId);

        return new ResponseEntity<>(
                service.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );


    }

}
