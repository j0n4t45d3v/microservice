package com.microservice.productservice.service.implementations;

import com.microservice.productservice.dto.ProductDto;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.ProductRepository;
import com.microservice.productservice.service.ProductService;
import io.javatab.microservices.util.MapperCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAll() {
        return this.productRepository.findAll()
                .stream().map(this::transformProductToProductDto)
                .toList();
    }

    @Override
    public ProductDto getById(Long id) {
        return this.productRepository.findById(id)
                .map(this::transformProductToProductDto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "produto não encontrado"
                ));
    }

    @Override
    public void create(ProductDto productDto) {
        this.productRepository.findByName(productDto.name())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "produto já cadastrado"
                ));

        Product product = Product.builder()
                .name(productDto.name())
                .brand(productDto.brand())
                .description(productDto.description())
                .price(productDto.price())
                .stock(productDto.stock())
                .categories(productDto.categories())
                .build();

        this.productRepository.save(product);
    }

    @Override
    public void update(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "produto não encontrado"
                ));

        this.copyNotNullProperties(productDto, product);

        this.productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "produto não encontrado"
                ));

        this.productRepository.deleteById(id);
    }

    private void copyNotNullProperties(ProductDto dto, Product product){
        MapperCustom.updatePropererIfNotNull(product::setName, dto.name());
        MapperCustom.updatePropererIfNotNull(product::setBrand, dto.brand());
        MapperCustom.updatePropererIfNotNull(product::setDescription, dto.description());
        MapperCustom.updatePropererIfNotNull(product::setPrice, dto.price());
        MapperCustom.updatePropererIfNotNull(product::setStock, dto.stock());
        MapperCustom.updatePropererIfNotNull(product::setCategories, dto.categories());
    }

    private ProductDto transformProductToProductDto(Product product) {
        return new ProductDto(product.getName(), product.getDescription(), product.getPrice(), product.getBrand(), product.getStock(), product.getCategories());
    }
}
