package uz.pdp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.spring.entity.Attachment;
import uz.pdp.spring.payload.*;
import uz.pdp.spring.service.AttachmentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/getAttachment")
    public ResponseEntity<List<Attachment>> getAttachment(){
        List<Attachment> attachmentList = attachmentService.getAttachment();
        return ResponseEntity.ok(attachmentList);
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE')")
    @GetMapping("/getAttachmentById/{id}")
    public ResponseEntity<Attachment> getAttachmentById(@PathVariable Integer id){
        Attachment attachmentById = attachmentService.getAttachmentById(id);
        return ResponseEntity.ok(attachmentById);
    }

        @PreAuthorize(value = "hasAuthority('ADD_ATTACHMENT')")
    @PostMapping("/addAttachment")
    public ResponseEntity<ApiResponse> addAttachment(@RequestBody MultipartHttpServletRequest request){
        ApiResponse apiResponse = attachmentService.addAttachment(request);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    @PreAuthorize(value = "hasAuthority('UP_ATTACHMENT')")
    @PutMapping("/updateAttachment/{id}")
    public ResponseEntity<ApiResponse> updateAttachment(@PathVariable Integer id,@RequestBody MultipartHttpServletRequest request){
        ApiResponse apiResponse = attachmentService.updateAttachment(id, request);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ATTACHMENT')")
    @DeleteMapping("/deleteAttachment/{id}")
    public ResponseEntity<ApiResponse> deleteAttachment(@PathVariable Integer id){
        ApiResponse apiResponse = attachmentService.deleteAttachment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
}
