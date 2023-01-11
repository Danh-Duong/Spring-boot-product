package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // todo
    public Product findByName(String name);

    // Product ở đây là tên của entity
    @Query("select p from Product p where p.name like :name%")
    public ArrayList<Product> findAllNameContain(@Param("name") String name);

    public List<Product> findByNameLike(String name);

    // products ở đây là tên trong database
//    ở đây sử dụng nativeQuery = true cho phép dử dụng mysql
    @Query(value = "Select * from products where name = ':name%'", nativeQuery = true)
    public ArrayList<Product> findDanh(@Param("name") String name);
}
