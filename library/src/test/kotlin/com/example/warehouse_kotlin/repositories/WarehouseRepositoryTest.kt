package com.example.warehouse_kotlin.repositories

import com.example.warehouse_kotlin.TestingApplication
import com.example.warehouse_kotlin.models.Warehouse
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = arrayOf(TestingApplication::class))
@ActiveProfiles("test")
class WarehouseRepositoryTest {

    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @AfterEach
    fun tearDown() {
        warehouseRepository.deleteAll()
    }

    @Nested
    @DisplayName("findAll")
    inner class FindAll {
        @Test
        fun `should return list of warehouses from database`() {
            warehouseRepository.save(Warehouse())

            val actual = warehouseRepository.findAll()

            assertEquals(1, actual.count())
        }
    }
}
