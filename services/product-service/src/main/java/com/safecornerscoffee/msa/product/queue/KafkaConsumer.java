package com.safecornerscoffee.msa.product.queue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safecornerscoffee.msa.product.entity.Product;
import com.safecornerscoffee.msa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ProductRepository productRepository;

    @KafkaListener(topics = "product-topic")
    public void updateProductQuantity(String message) {
        log.info("Kafka Message: {}", message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Product product = productRepository.findByProductId((String) map.get("productId"))
                .orElseThrow();

        product.setStock(product.getStock() - (Integer)map.get("quantity"));
        productRepository.save(product);
    }
}
