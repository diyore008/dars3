package uz.pdp.spring.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.spring.entity.Attachment;
import uz.pdp.spring.entity.AttachmentContent;
import uz.pdp.spring.payload.*;
import uz.pdp.spring.repository.AttachmentContentRepository;
import uz.pdp.spring.repository.AttachmentRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public List<Attachment> getAttachment(){
        List<Attachment> attachmentList = attachmentRepository.findAll();
        return attachmentList;
    }

    public Attachment  getAttachmentById(Integer id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return optionalAttachment.get();
    }

    @SneakyThrows
    public ApiResponse addAttachment(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment saveAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(saveAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new ApiResponse("Saved successfully",true );
    }

    @SneakyThrows
    public ApiResponse updateAttachment(Integer id, MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if(!optionalAttachment.isPresent())
            return new ApiResponse("Attachment not found", false);
        Attachment attachment = optionalAttachment.get();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment saveAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(saveAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new ApiResponse("Update attachment", true);
    }


    public ApiResponse deleteAttachment(Integer id){
        try {
            attachmentRepository.findById(id);
            return new ApiResponse("Deleted attachment", true);
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
}
