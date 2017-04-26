package com.inventory.service;

import com.inventory.dao.Inventory;
import com.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by associate on 4/18/17.
 */
@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    InventoryRepository repository;

    @Override
    public Inventory addInventory(Inventory inventory) {
        return repository.save(inventory);
    }

    @Override
    public List<Inventory> getInventories() {
        return repository.findAll();
    }

    @Override
    public Inventory getInventoryForSkuAndStoreNbr(Integer sku, Integer storeNbr) {
        return repository.findBySkuAndStoreNbr(sku, storeNbr);
    }
}
