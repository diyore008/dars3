package uz.pdp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.spring.entity.Basket;
import uz.pdp.spring.payload.ApiResponse;
import uz.pdp.spring.payload.BasketDto;
import uz.pdp.spring.service.BasketService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BasketController {

    @Autowired
    BasketService basketService;

    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/getBaskets")
    public ResponseEntity<List<Basket>> getBaskets(){
        List<Basket> basketList = basketService.getBaskets();
        return ResponseEntity.ok(basketList);
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE')")
    @GetMapping("/getBasketById/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable Integer id){
        Basket basketById = basketService.getBasketById(id);
        return ResponseEntity.ok(basketById);
    }

    @PreAuthorize(value = "hasAuthority('ADD')")
    @PostMapping("/addBasket")
    public ResponseEntity<ApiResponse> addBasket(@RequestBody BasketDto basketDto){
        ApiResponse apiResponse = basketService.addBasket(basketDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('UP')")
    @PutMapping("/updateBasket/{id}")
    public ResponseEntity<ApiResponse> updateBasket(@PathVariable Integer id, @RequestBody BasketDto basketDto){
        ApiResponse apiResponse = basketService.updateBasket(id, basketDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize(value = "hasAuthority('DELETE')")
    @DeleteMapping("/deleteBasket/{id}")
    public ResponseEntity<ApiResponse> deleteBasket(@PathVariable Integer id){
        ApiResponse apiResponse = basketService.deleteBasket(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
