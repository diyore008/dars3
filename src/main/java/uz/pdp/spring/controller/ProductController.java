package uz.pdp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.spring.entity.Product;
import uz.pdp.spring.payload.ApiResponse;
import uz.pdp.spring.payload.ProductDto;
import uz.pdp.spring.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;


    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> productsList = productService.getProducts();
        return ResponseEntity.ok(productsList);
    }


    @PreAuthorize(value = "hasAuthority('READ_ONE')")
    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        Product productById = productService.getProductById(id);
        return ResponseEntity.ok(productById);
    }


    @PreAuthorize(value = "hasAuthority('ADD')")
    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('UP')")
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.updateProduct(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE')")
    @DeleteMapping("/deletedProduct/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id){
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
