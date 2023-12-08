package com.tskr.payment.service;

import com.tskr.payment.payload.request.PaymentRequest;
import com.tskr.payment.payload.response.PaymentResponse;

public interface PaymentService {

    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
