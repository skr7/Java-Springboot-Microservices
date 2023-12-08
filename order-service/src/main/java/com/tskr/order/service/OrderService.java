package com.tskr.order.service;

import com.tskr.order.payload.request.OrderRequest;
import com.tskr.order.payload.response.OrderResponse;

public interface OrderService {


    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
