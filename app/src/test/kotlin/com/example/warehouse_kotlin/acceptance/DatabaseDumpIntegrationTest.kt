package com.example.warehouse_kotlin.acceptance

import com.example.warehouse_kotlin.WarehouseKotlinApplication
import com.example.warehouse_kotlin.models.Warehouse
import com.example.warehouse_kotlin.repositories.WarehouseRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import testutilities.TestUtils
import javax.sql.DataSource

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = arrayOf(WarehouseKotlinApplication::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseDumpIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var dataSource: DataSource

    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @BeforeAll
    fun setup() {
        val connection = dataSource.connection
        ScriptUtils.executeSqlScript(connection, ClassPathResource("data.sql"))
    }

    @AfterAll
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
