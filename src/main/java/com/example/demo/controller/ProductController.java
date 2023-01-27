package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/list")
    public String listProduct(Model model){
        return productService.listProduct(model);
    }

    @GetMapping("/add")
    public String addProductForm(Model model){
        return productService.addProductForm(model);
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,Model model){
        return productService.addProduct(product,model);
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }


    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable Integer id,Model model){
        return productService.infoProduct(id,model);
    }



    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model){
        return productService.editForm(id, model);
    }

    // để {id} để biết product nào đang được eidt
    @PostMapping("/edit/{id}")
    public String editProduct(@ModelAttribute(name = "product") Product product){
        return productService.editProduct(product);
    }


    @GetMapping("/search")
//    Chú ý: RequestParam sẽ lấy được giá trị của name trong file html sau đó đưa vào url.
    public String searchProduct(@RequestParam(name = "name", defaultValue = "", required = false) String name,Model model){
        return productService.searchProduct(name, model);
    }

    //RequestMapping có nghĩa là có thể sử dụng trên tất cả các method
    @RequestMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("oke");
    }

}
