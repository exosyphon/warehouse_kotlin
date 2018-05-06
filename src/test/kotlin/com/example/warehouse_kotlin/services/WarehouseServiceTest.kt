package com.example.warehouse_kotlin.services

import com.example.warehouse_kotlin.models.Warehouse
import com.example.warehouse_kotlin.repositories.WarehouseRepository
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WarehouseServiceTest {
    @Mock
    private lateinit var mockWarehouseRepository: WarehouseRepository

    @Test
    fun findAll() {
        val warehouses = mutableListOf(Warehouse())
        Mockito.`when`(mockWarehouseRepository.findAll()).thenReturn(warehouses)
        assertEquals(WarehouseService(mockWarehouseRepository).findAll(), warehouses)
    }
}