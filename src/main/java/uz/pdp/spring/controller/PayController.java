package uz.pdp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.spring.entity.Pay;
import uz.pdp.spring.payload.ApiResponse;
import uz.pdp.spring.payload.PayDto;
import uz.pdp.spring.service.PayService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PayController {

    @Autowired
    PayService payService;

    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/getPays")
    public ResponseEntity<List<Pay>> getPays(){
        List<Pay> payList = payService.getPays();
        return ResponseEntity.ok(payList);
    }

    @PreAuthorize(value = "hasAnyAuthority('READ_ONE')")
    @GetMapping("/getPayById/{id}")
    public ResponseEntity<Pay> getPayById(@PathVariable Integer id){
        Pay payById = payService.getPayById(id);
        return ResponseEntity.ok(payById);
    }

    @PreAuthorize(value = "hasAuthority('ADD')")
    @PostMapping("/addPay")
    public ResponseEntity<ApiResponse> addPay(@RequestBody PayDto payDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApiResponse apiResponse = payService.addPay(payDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyAuthority('UP')")
    @PutMapping("/updatePay/{id}")
    public ResponseEntity<ApiResponse> updatePay(@PathVariable Integer id, @RequestBody PayDto payDto){
        ApiResponse apiResponse = payService.updatePay(id, payDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE')")
    @DeleteMapping("/deletePay/{id}")
    public ResponseEntity<ApiResponse> deletePay(@PathVariable Integer id){
        ApiResponse apiResponse = payService.deletePay(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
