package com.example.warehouse_kotlin.repositories

import com.example.warehouse_kotlin.models.Warehouse
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
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
