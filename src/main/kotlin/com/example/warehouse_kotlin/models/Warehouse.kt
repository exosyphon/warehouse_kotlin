package com.example.warehouse_kotlin.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Warehouse(
        var name: String = "",
        var address: String = "",
        var city: String = "",
        var state: String = "",
        var zip: String = "",
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        var id: Long = 0
)
