package com.tskr.product.repository;

import com.tskr.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public Product getProductByProductId(long productId);
}
