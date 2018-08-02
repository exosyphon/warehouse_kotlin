package com.example.warehouse_kotlin.acceptance

import com.example.warehouse_kotlin.WarehouseKotlinApplication
import com.example.warehouse_kotlin.models.Warehouse
import com.example.warehouse_kotlin.repositories.WarehouseRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import testutilities.TestUtils

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = arrayOf(WarehouseKotlinApplication::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WarehouseIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @BeforeEach
    fun setup() {
        warehouseRepository.saveAll(
                listOf(
                        Warehouse("warehouse 1", "1234 Bellvedere Way", "Tom Collins", "TX", "79707"),
                        Warehouse("warehouse 2", "665 1st St", "San Francisco", "CA", "90210")
                )
        )
    }

    @AfterEach
    fun tearDown() {
        warehouseRepository.deleteAll()
    }

    @Nested
    @DisplayName("index")
    inner class Index {
        @Test
        fun `should return json list of warehouses`() {
            val perform = mockMvc.perform(get("/"))

            val findAll: List<Warehouse> = warehouseRepository.findAll() as List<Warehouse>
            val values: Map<String, Any> = mapOf(Pair("id1", findAll[0].id), Pair("id2", findAll[1].id))

            perform.andExpect(status().isOk)
                    .andExpect(content().json(TestUtils.readJsonFixture("warehouse-response/get_warehouses.json", values), true))
        }
    }
}
