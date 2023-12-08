package com.tskr.product.service;

import com.tskr.product.payload.request.ProductRequest;
import com.tskr.product.payload.response.ProductResponse;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);

    public void deleteProductById(long productId);
}
