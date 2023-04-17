package uz.pdp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.spring.entity.Attachment;
import uz.pdp.spring.entity.Category;
import uz.pdp.spring.entity.Info;
import uz.pdp.spring.entity.Product;
import uz.pdp.spring.payload.ApiResponse;
import uz.pdp.spring.payload.ProductDto;
import uz.pdp.spring.repository.*;
import uz.pdp.spring.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    InfoRepository infoRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getProducts(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.get();
    }

    public ApiResponse addProduct(ProductDto productDto){
        Optional<Info> optionalInfo = infoRepository.findById(productDto.getInfoId());
        if (!optionalInfo.isPresent())
            return new ApiResponse("Info not found",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Attachment not found", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setInfo(optionalInfo.get());
        product.setAttachment(optionalAttachment.get());
        product.setCategory(optionalCategory.get());
        productRepository.save(product);
        return new ApiResponse("Saved product",true);
    }

    public ApiResponse updateProduct(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product not found",false );
        Optional<Info> optionalInfo = infoRepository.findById(productDto.getInfoId());
        if (!optionalInfo.isPresent())
            return new ApiResponse("Info not found",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Attachment not found", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Category not found", false);

        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setInfo(optionalInfo.get());
        product.setAttachment(optionalAttachment.get());
        product.setCategory(optionalCategory.get());
        productRepository.save(product);
        return new ApiResponse("Updated product", true);
    }

    public ApiResponse deleteProduct(Integer id){
        try {
            productRepository.deleteById(id);
            return new ApiResponse("Deleted product", true);
        }catch (Error e){
            return new ApiResponse("Error", false);
        }
    }
}
