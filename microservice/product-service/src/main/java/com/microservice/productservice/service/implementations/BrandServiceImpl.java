package com.microservice.productservice.service.implementations;

import com.microservice.productservice.dto.BrandDto;
import com.microservice.productservice.model.Brand;
import com.microservice.productservice.repository.BrandRepository;
import com.microservice.productservice.service.BrandService;
import io.javatab.microservices.util.MapperCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<BrandDto> getAll() {
        return this.brandRepository.findAll()
                .stream()
                .map(this::transformBrandToBrandDto)
                .toList();
    }

    @Override
    public BrandDto getById(Long id) {
        return this.brandRepository.findById(id)
                .map(this::transformBrandToBrandDto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "marca não encontrada"
                ));
    }

    @Override
    public void create(BrandDto brandDto) {
        this.brandRepository.findByName(brandDto.name())
                .ifPresent(c -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "marca " + c.getName().toUpperCase() + " já ta cadastrada"
                    );
                });

        Brand brand = Brand.builder()
                .name(brandDto.name())
                .build();

        this.brandRepository.save(brand);
    }

    @Override
    public void update(Long id, BrandDto brandDto) {
        Brand brand = this.brandRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "marca não encontrada"
                ));

        this.brandRepository.findByName(brandDto.name())
                .ifPresent(c -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "marca " + c.getName().toUpperCase() + " já ta cadastrada"
                    );
                });

        this.copyNotNullProperties(brandDto, brand);

        this.brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        this.brandRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "marca não encontrada"
                ));

        this.brandRepository.deleteById(id);
    }

    private BrandDto transformBrandToBrandDto(Brand brand) {
        return BrandDto.BrandFromBrandDto(brand);
    }

    private void copyNotNullProperties(BrandDto dto, Brand brand) {
        MapperCustom.updatePropererIfNotNull(brand::setName, dto.name());
    }
}
