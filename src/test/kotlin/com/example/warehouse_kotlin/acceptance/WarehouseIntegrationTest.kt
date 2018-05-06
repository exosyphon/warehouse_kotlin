package com.example.warehouse_kotlin.acceptance

import com.example.warehouse_kotlin.models.Warehouse
import com.example.warehouse_kotlin.repositories.WarehouseRepository
import testutilities.TestUtils
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WarehouseIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @Before
    fun setup() {
        warehouseRepository.saveAll(
                listOf(
                        Warehouse("warehouse 1", "1234 Bellvedere Way", "Tom Collins", "TX", "79707", 1L),
                        Warehouse("warehouse 2", "665 1st St", "San Francisco", "CA", "90210", 2L)
                )
        )
    }

    @Test
    fun testIndex() {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().json(TestUtils.readJsonFixture("warehouse-response/get_warehouses.json"), true))
    }
}