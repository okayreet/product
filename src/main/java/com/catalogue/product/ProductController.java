package com.catalogue.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogue.product.rabbitmq.RabbitMQMessageProducer;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final TopicExchange topicExchange;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        log.info("request all products available");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{product_id}")
    public void getProductResponseById(@PathVariable Long product_id) {
        log.info("request getProductResponseByProductId: {}", product_id);
        rabbitMQMessageProducer.publish(productService.getProductById(product_id), topicExchange.getName(), "productKey");
    }
}
