package com.tskr.product.service.impl;

import com.tskr.product.entity.Product;
import com.tskr.product.exception.ProductServiceCustomException;
import com.tskr.product.payload.request.ProductRequest;
import com.tskr.product.payload.response.ProductResponse;
import com.tskr.product.repository.ProductRepository;
import com.tskr.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    @Override
    public long addProduct(ProductRequest productRequest) {

        log.info("ProductServiceImpl | addProduct is called");

        Product product=Product.builder().
                productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        product=repository.save(product);

        log.info("ProductServiceImpl | addProduct | Product Created");
        log.info("ProductServiceImpl | addProduct | Product Id : " + product.getProductId());


        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {

        log.info("ProductServiceImpl | getProductById is called");
        log.info("ProductServiceImpl | getProductById | Get the product for productId: {}", productId);

        Product product=repository.findById(productId).orElseThrow(
                ()->new ProductServiceCustomException("Product with given Id is not found","PRODUCT_NOT_FOUND")
        );


       return ProductResponse.builder()
               .productId(productId)
               .productName(product.getProductName())
               .quantity(product.getQuantity())
               .price(product.getPrice())
               .build();
        /*ProductResponse productResponse=new ProductResponse();
        productResponse.setProductId(productId);
        productResponse.setProductName(product.getProductName());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setPrice(product.getPrice());
        log.info("ProductServiceImpl | getProductById | productResponse :" + productResponse.toString());*/

        //return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {


        log.info("Reduce Quantity {} for Id: {}", quantity,productId);

        Product product
                = repository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        repository.save(product);
        log.info("Product Quantity updated Successfully");

    }

    @Override
    public void deleteProductById(long productId) {


        log.info("Product id: {}", productId);

        if (!repository.existsById(productId)) {
            log.info("Im in this loop {}", !repository.existsById(productId));
            throw new ProductServiceCustomException(
                    "Product with given with Id: " + productId + " not found:",
                    "PRODUCT_NOT_FOUND");
        }
        log.info("Deleting Product with id: {}", productId);
        repository.deleteById(productId);

    }
}
