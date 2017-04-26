package com.inventory.controller;

import com.inventory.dao.Inventory;
import com.inventory.service.InventoryService;
import com.inventory.validation.InventoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by associate on 4/18/17.
 */
@RestController
@RequestMapping("/")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    InventoryValidator inventoryValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setValidator(inventoryValidator);
    }

    @RequestMapping(value="inventories", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Inventory addInventory(@Validated @RequestBody Inventory inventory){
        return inventoryService.addInventory(inventory);
    }

    @RequestMapping(value="inventories", method= RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Inventory> getInventories(){
        return inventoryService.getInventories();
    }

    @RequestMapping(value="inventory", method= RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Inventory getInventory(@RequestParam("sku") Integer sku, @RequestParam("storeNbr") Integer storeNbr){
        return inventoryService.getInventoryForSkuAndStoreNbr(sku, storeNbr);
    }
}
