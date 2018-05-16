package com.example.warehouse_kotlin.repositories

import com.example.warehouse_kotlin.models.Warehouse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
class WarehouseRepositoryTest {

    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @Test
    fun findAll() {
        warehouseRepository.save(Warehouse())

        val actual = warehouseRepository.findAll()

        assertEquals(1, actual.count())
    }
}
