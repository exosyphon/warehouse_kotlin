package com.example.warehouse_kotlin.controllers

import com.example.warehouse_kotlin.models.Warehouse
import com.example.warehouse_kotlin.services.WarehouseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class WarehouseController(val warehouseService: WarehouseService) {
    @RequestMapping("/")
    fun index(): ResponseEntity<List<Warehouse>> = ResponseEntity.ok(warehouseService.findAll())
}