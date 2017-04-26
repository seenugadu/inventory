package com.inventory.controller

import com.inventory.dao.Inventory
import com.inventory.service.InventoryService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by associate on 4/25/17.
 */
class InventoryControllerSpec extends Specification{

    private InventoryService inventoryService

    private MockMvc mockMvc

    def setup(){
        inventoryService = Mock()
        def controllerTest = new InventoryController(inventoryService: inventoryService)
        mockMvc = MockMvcBuilders.standaloneSetup(controllerTest).build()
    }

    def "GET a inventory for sku and store"(){

        given: "SKU and product name"
        def sku = 110
        def storeNbr = 9001
        def quantity = 10
        def inventory = new Inventory(sku: 110, storeNbr: 9001, quantity: 10)

        when: "Fetch inventory for sku and store"

        def result = mockMvc.perform(get("/inventory")
                .param("sku","110")
                .param("storeNbr", "9001")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

        then: "Mock the service to fetch inventory for sku and store"
            inventoryService.getInventoryForSkuAndStoreNbr(sku,storeNbr) >> inventory

            result.andExpect(status().isOk())
            result.andExpect(jsonPath("\$.sku").value(sku))
            result.andExpect(jsonPath("\$.storeNbr").value(storeNbr))
            result.andExpect(jsonPath("\$.quantity").value(quantity))

    }

    def "GET all inventories"(){

        given: "2 inventories"
            def inventory1 = new Inventory(sku: 110, storeNbr: 9001, quantity: 10)
            def inventory2 = new Inventory(sku: 111, storeNbr: 9002, quantity: 11)
            List<Inventory> inventories = new ArrayList<>()
            inventories.add(inventory1)
            inventories.add(inventory2)

        when: "Fetch all inventories"

        def result = mockMvc.perform(get("/inventories")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

        then: "Mock the service to fetch all inventories"
            inventoryService.getInventories() >> inventories

        result.andExpect(status().isOk())
        result.andExpect(jsonPath("\$.[0].sku").value(110))
        result.andExpect(jsonPath("\$.[1].storeNbr").value(9002))
        result.andExpect(jsonPath("\$.[1].quantity").value(11))


    }

    def "ADD a inventory"(){

        given: "Set the json body request"
        def jsonBody = "{\"sku\":110, \"quantity\":10,\"storeNbr\":9001}"
        def inventory = new Inventory(sku: 110, storeNbr: 9001, quantity: 10)

        when: "Add a inventory"

        def result = mockMvc.perform(post("/inventories")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonBody.getBytes()))


        then: "Mock the service to add a inventory"
        inventoryService.addInventory(_ as Inventory) >> inventory

        result.andExpect(status().isOk())
        result.andExpect(jsonPath("\$.sku").value(110))
        result.andExpect(jsonPath("\$.storeNbr").value(9001))
        result.andExpect(jsonPath("\$.quantity").value(10))
    }
}
