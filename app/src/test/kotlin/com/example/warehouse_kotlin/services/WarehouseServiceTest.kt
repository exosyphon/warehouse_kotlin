package com.example.warehouse_kotlin.services

import com.example.warehouse_kotlin.models.Warehouse
import com.example.warehouse_kotlin.repositories.WarehouseRepository
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalTime

@ExtendWith(MockitoExtension::class)
class WarehouseServiceTest {
    @Mock
    private lateinit var mockWarehouseRepository: WarehouseRepository

    @Nested
    @DisplayName("findAll")
    inner class FindAll {
        @Test
        fun `should return list of warehouses from repo`() {
            val warehouses = mutableListOf(Warehouse())
            Mockito.`when`(mockWarehouseRepository.findAll()).thenReturn(warehouses)
            assertEquals(WarehouseService(mockWarehouseRepository).findAll(), warehouses)
        }

        @Test
        fun `should deserialize an object with local date and local time`() {
            val mapper = ObjectMapper()
            mapper.registerModule(JavaTimeModule())
            val ticket = mapper.readValue<List<Ticket>>("""[{ "contact_email": "contact@ema.il", "date_opened": "2017-11-13", "time_opened": "00:04:00.456" }]""")
            assert("$ticket" == "[Ticket(contactEmail=contact@ema.il, dateOpened=2017-11-13, timeOpened=00:04:00.456)]")
        }
    }
}

data class Ticket(
        @JsonProperty("contact_email")
        var contactEmail: String? = null,
        @JsonProperty("date_opened")
        var dateOpened: LocalDate? = null,
        @JsonProperty("time_opened")
        var timeOpened: LocalTime? = null
)
