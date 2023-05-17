package com.example.microserviceorder.service;

import com.example.microserviceorder.client.ClientProduct;
import com.example.microserviceorder.model.Items;
import com.example.microserviceorder.repository.ItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(ItemsService.class);

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private ClientProduct clientProduct;

    public void addItems(Items items) {
        List<String> productsName = this.clientProduct.getAll().stream().map(e -> e.getClass().getName()).toList();

        if (productsName.contains(items.getProductName())) {
            LOGGER.info("Produto {} existe e foi adicionado ao pedido", items.getProductName());
            itemsRepository.save(items);
        }

    }
}
