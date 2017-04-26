package com.inventory.validation

import com.inventory.dao.Inventory
import org.springframework.validation.BindException
import spock.lang.Specification
/**
 * Created by associate on 4/25/17.
 */
class InventoryValidatorSpec extends Specification{

    InventoryValidator inventoryValidator

    BindException bindException

    Inventory inventory

    void setup(){
        inventoryValidator = new InventoryValidator()
        inventory = new Inventory();
        bindException = new BindException(inventory, "inventory");
    }

    def "Validate quantity of inventory"(){

        given:
            inventory.setQuantity(0)
            inventory.setSku(111)
            inventory.setStoreNbr(9001)

        when:
            inventoryValidator.validate(inventory,bindException)

        then:
            bindException.getAllErrors().stream().anyMatch({error -> error.getCode().equalsIgnoreCase("5001")}) == true


    }

    def "Validate SKU"(){

        given:
            inventory.setQuantity(10)
            inventory.setSku(0)
            inventory.setStoreNbr(9001)

        when:
            inventoryValidator.validate(inventory,bindException)

        then:
            bindException.getAllErrors().stream().anyMatch({error -> error.getCode().equalsIgnoreCase("5002")}) == true

    }

    def "Validate store Number"(){

        given:
            inventory.setQuantity(11)
            inventory.setSku(110)
            inventory.setStoreNbr(1)

        when:
            inventoryValidator.validate(inventory,bindException)

        then:
            bindException.getAllErrors().stream().anyMatch({error -> error.getCode().equalsIgnoreCase("5004")}) == true

    }


}
