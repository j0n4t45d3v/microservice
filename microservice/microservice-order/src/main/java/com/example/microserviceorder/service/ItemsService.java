package com.example.microserviceorder.service;

import com.example.microserviceorder.model.Items;
import com.example.microserviceorder.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    public void addItems(Items items){
        itemsRepository.save(items);
    }
}
