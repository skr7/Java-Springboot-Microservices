package com.tskr.product.controller;


import com.tskr.product.payload.request.ProductRequest;
import com.tskr.product.payload.response.ProductResponse;
import com.tskr.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {


    private final ProductService productService;




    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {

        log.info("ProductController | addProduct is called");

        log.info("ProductController | addProduct | productRequest : " + productRequest.toString());

        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId) {

        log.info("ProductController | getProductById is called");

        log.info("ProductController | getProductById | productId : " + productId);

        ProductResponse productResponse
                = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }



    // http://localhost:9001/product/reduceQuantity/1?quantity=0
    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    ) {

        log.info("ProductController | reduceQuantity is called");

        log.info("ProductController | reduceQuantity | productId : " + productId);
        log.info("ProductController | reduceQuantity | quantity : " + quantity);

        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") long productId) {
        productService.deleteProductById(productId);
    }


}
