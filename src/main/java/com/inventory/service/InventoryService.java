package com.inventory.service;

import com.inventory.dao.Inventory;

import java.util.List;

/**
 * Created by associate on 4/18/17.
 */
public interface InventoryService {

    Inventory addInventory(Inventory inventory);

    List<Inventory> getInventories();

    Inventory getInventoryForSkuAndStoreNbr(Integer sku, Integer storeNbr);
}
