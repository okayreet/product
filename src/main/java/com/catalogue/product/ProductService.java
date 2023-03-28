package com.catalogue.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.catalogue.product.rabbitmq.RabbitMQMessageProducer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long product_id) {
        return productRepository.findById(product_id).get();
    }

}