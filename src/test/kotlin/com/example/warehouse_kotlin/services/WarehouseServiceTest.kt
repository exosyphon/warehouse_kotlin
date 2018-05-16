package com.example.warehouse_kotlin.services

import com.example.warehouse_kotlin.models.Warehouse
import com.example.warehouse_kotlin.repositories.WarehouseRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class WarehouseServiceTest {
    @Mock
    private lateinit var mockWarehouseRepository: WarehouseRepository

    @Test
    fun `findAll should return list of warehouses from repo`() {
        val warehouses = mutableListOf(Warehouse())
        Mockito.`when`(mockWarehouseRepository.findAll()).thenReturn(warehouses)
        assertEquals(WarehouseService(mockWarehouseRepository).findAll(), warehouses)
    }
}