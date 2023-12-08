package com.tskr.payment.service;

import com.tskr.payment.entity.TransactionDetails;
import com.tskr.payment.exception.PaymentServiceCustomException;
import com.tskr.payment.payload.request.PaymentRequest;
import com.tskr.payment.payload.response.PaymentResponse;
import com.tskr.payment.repository.TransactionDetailsRepository;
import com.tskr.payment.util.PaymentMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final TransactionDetailsRepository repository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {


        log.info("PaymentServiceImpl | doPayment is called");

        log.info("PaymentServiceImpl | doPayment | Recording Payment Details: {}", paymentRequest);


        TransactionDetails transactionDetails=TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetails=repository.save(transactionDetails);

        log.info("Transaction Completed with Id: {}", transactionDetails.getId());


        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {


        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId is called");

        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId | Getting payment details for the Order Id: {}", orderId);


        TransactionDetails transactionDetails= repository.findByOrderId(orderId)
                .orElseThrow(()->new PaymentServiceCustomException
                        ("Transaction Details with the Order id is not found","TRANSACTION_NOT_FOUND"));

        PaymentResponse paymentResponse=
                PaymentResponse.builder()
                        .paymentId(transactionDetails.getId())
                        .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                        .paymentDate(transactionDetails.getPaymentDate())
                        .orderId(transactionDetails.getOrderId())
                        .status(transactionDetails.getPaymentStatus())
                        .amount(transactionDetails.getAmount())
                        .build();

        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId | paymentResponse: {}", paymentResponse.toString());



        return paymentResponse;
    }
}
