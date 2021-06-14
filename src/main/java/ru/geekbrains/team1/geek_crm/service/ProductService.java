package ru.geekbrains.team1.geek_crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.team1.geek_crm.entities.Product;
import ru.geekbrains.team1.geek_crm.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

}