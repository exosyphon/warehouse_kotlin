package com.example.warehouse_kotlin.repositories

import com.example.warehouse_kotlin.models.Warehouse
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WarehouseRepository : CrudRepository<Warehouse, Long>