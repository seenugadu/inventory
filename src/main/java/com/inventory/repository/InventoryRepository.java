package com.inventory.repository;

import com.inventory.dao.Inventory;
import com.inventory.dao.InventoryId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by associate on 4/18/17.
 */
@RepositoryRestResource
public interface InventoryRepository extends CrudRepository<Inventory, InventoryId>{

    List<Inventory> findAll();

    Inventory save(Inventory inventory);

    Inventory findBySkuAndStoreNbr(Integer sku, Integer storeNbr);
}
