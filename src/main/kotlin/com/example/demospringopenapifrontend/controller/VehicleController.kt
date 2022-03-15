package com.example.demospringopenapifrontend.controller

import com.example.demospringopenapifrontend.integration.VehicleServiceClient
import com.example.demospringopenapifrontend.integration.generated.VehicleControllerApi
import com.example.demospringopenapifrontend.model.Vehicle
import com.example.demospringopenapifrontend.model.generated.VehicleGenerated
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


@Controller
class VehicleController(
        val vehicleServiceClient: VehicleServiceClient,
        val vehicleControllerApi: VehicleControllerApi
) {

    @GetMapping("/add-vehicle")
    fun addVehiclePage(vehicle: Vehicle, model: Model): String {
        model.addAttribute("vehicle", vehicle)
        return "add-vehicle"
    }

    @GetMapping("/index")
    fun indexPage(model: Model): String {
        model.addAttribute("vehicles", vehicleControllerApi.allVehicles)
        return "index"
    }

    @PostMapping("/addVehicle")
    fun addVehicle(vehicle: Vehicle?, result: BindingResult, model: Model): String {
        vehicleControllerApi.saveVehicle(
            VehicleGenerated().apply {
            id = vehicle?.id
            registrationNumber = vehicle?.registrationNumber
        })
        return "redirect:/index"
    }

}