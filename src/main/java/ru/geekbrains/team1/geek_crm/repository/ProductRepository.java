package ru.geekbrains.team1.geek_crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.team1.geek_crm.entities.Product;

@Repository
public interface ProductRepository extends
        JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Product findFirstByTitle(String title);

    Product findFirstByVendorCode(String vendorCode);

}
