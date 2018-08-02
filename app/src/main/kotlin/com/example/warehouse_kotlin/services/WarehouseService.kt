package com.example.warehouse_kotlin.services

import com.example.warehouse_kotlin.models.Warehouse
import com.example.warehouse_kotlin.repositories.WarehouseRepository
import org.springframework.stereotype.Service

@Service
class WarehouseService(val warehouseRepository: WarehouseRepository) {
    fun findAll(): List<Warehouse> = warehouseRepository.findAll() as List<Warehouse>
}