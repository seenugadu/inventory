package com.inventory.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by associate on 4/18/17.
 */
@Entity
@IdClass(InventoryId.class)
public class Inventory {

    @Id
    Integer sku;
    @Id
    Integer storeNbr;

    Integer quantity;

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Integer getStoreNbr() {
        return storeNbr;
    }

    public void setStoreNbr(Integer storeNbr) {
        this.storeNbr = storeNbr;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
