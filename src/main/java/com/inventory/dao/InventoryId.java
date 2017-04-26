package com.inventory.dao;

import java.io.Serializable;

/**
 * Created by associate on 4/18/17.
 */
public class InventoryId implements Serializable {

    Integer sku;
    Integer storeNbr;

    public InventoryId(){}

    public InventoryId(Integer sku, Integer storeNbr){
        this.sku = sku;
        this.storeNbr = storeNbr;
    }

    public Integer getSku() {
        return sku;
    }

    public Integer getStoreNbr() {
        return storeNbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryId that = (InventoryId) o;

        if (sku != null ? !sku.equals(that.sku) : that.sku != null) return false;
        return storeNbr != null ? storeNbr.equals(that.storeNbr) : that.storeNbr == null;

    }

    @Override
    public int hashCode() {
        int result = sku != null ? sku.hashCode() : 0;
        result = 31 * result + (storeNbr != null ? storeNbr.hashCode() : 0);
        return result;
    }
}
