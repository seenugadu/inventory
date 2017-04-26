package com.inventory.validation;

import com.inventory.dao.Inventory;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by associate on 4/21/17.
 */
@Component
public class InventoryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Inventory.class.equals(clazz) ;
    }

    @Override
    public void validate(Object target, Errors errors) {

        Inventory inventory = (Inventory) target;
        validateQantity(inventory.getQuantity(), errors);
        validSKU(inventory.getSku(),errors);
        validateStoreNbr(inventory.getStoreNbr(), errors);

    }

    private void validateQantity(Integer qantity, Errors errors){
        if (NumberUtils.INTEGER_ZERO.compareTo(qantity) >= 0) {
            errors.reject("5001");
        }
    }

    private void validSKU(Integer sku, Errors errors){

        if(NumberUtils.INTEGER_ZERO.compareTo(sku) >= 0){
            errors.reject("5002");
        }
    }

    private void validateStoreNbr(Integer storeNbr, Errors errors){

        String pattern = "\\d{4,6}";
        boolean isMatch = Pattern.matches(pattern, storeNbr.toString());
        if (!isMatch) {
            errors.reject("5004");
        }
    }
}
