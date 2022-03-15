package com.example.demospringopenapifrontend.integration

import com.example.demospringopenapifrontend.model.Vehicle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

const val vehicleApiUrl = "http://localhost:8080/api/vehicle"
private val logger: Logger = LoggerFactory.getLogger(VehicleServiceClient::class.java)

@Component
class VehicleServiceClient(
        private val restTemplate: RestTemplate
) {
    fun getVehicle(id: Int): Vehicle? =
            restTemplate.getForEntity(vehicleApiUrl, Vehicle::class.java, id).body

    fun getVehicles(): List<Vehicle?>? {
        val response = restTemplate.getForEntity(vehicleApiUrl, List::class.java).body as List<Vehicle>
        logger.info("Response from vehicle service: {}", response)
        return response
    }

    fun storeVehicle(vehicle: Vehicle?) {
        restTemplate.put(vehicleApiUrl, vehicle)
    }
}

@Configuration
class VehicleServiceConfiguration {
    @Bean
    fun restTemplate() = RestTemplate()
}