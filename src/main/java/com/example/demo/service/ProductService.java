package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public String listProduct(Model model){
        model.addAttribute("list",productRepository.findAll());
        return "list";
    }

    public String deleteProduct(Integer id){
        Product product=productRepository.findById(id).get();
        productRepository.delete(product);
        return "redirect:/list";
    }


    public String addProductForm(Model model){
        model.addAttribute("product",new Product());
        return "addProductForm";
    }

    public String addProduct(Product product, Model model){
        Product product1=productRepository.findByName(product.getName());
        if (product.getName().equals("")==false && product.getManufacture().equals("")==false && product1==null){
            productRepository.save(product);
            return "redirect:/list";
        }

        model.addAttribute("error","Không thể thêm sản phầm. Vui lòng thử lại");
        return "addProductForm";
    }


    public String infoProduct(Integer id,Model model){
        Product product=productRepository.findById(id).get();
        model.addAttribute("product",product);
        return "info";
    }

    public String editForm(Integer id, Model model){
        Product product=productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "editForm";
    }

    public String editProduct(Product product){
        productRepository.save(product);
        return "redirect:/list";
    }

    // có thể gộp get list user và search thành 1
    public String searchProduct(String name,Model model){
        List<Product> list= productRepository.findAllNameContain(name);
        model.addAttribute("list",list);
        return "list";
    }
}
