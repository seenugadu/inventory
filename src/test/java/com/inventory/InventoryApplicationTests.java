package com.inventory;

import com.inventory.dao.Inventory;
import com.inventory.service.InventoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryApplicationTests {

	@Autowired
	InventoryService inventoryService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getInventories(){
		addInventory();

		List<Inventory> inventoryList = inventoryService.getInventories();
		Assert.assertNotNull(inventoryList);
		Assert.assertTrue(inventoryList.size() == 3);

	}

	private void addInventory(){

		Inventory inventory = new Inventory();
		inventory.setSku(123);
		inventory.setStoreNbr(900);
		inventory.setQuantity(3);
		inventoryService.addInventory(inventory);

		inventory.setSku(234);
		inventory.setStoreNbr(901);
		inventory.setQuantity(5);
		inventoryService.addInventory(inventory);

		inventory.setSku(333);
		inventory.setStoreNbr(901);
		inventory.setQuantity(6);
		inventoryService.addInventory(inventory);
		inventory.setQuantity(7);
		inventoryService.addInventory(inventory);
	}
}
