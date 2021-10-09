package com.ms.printing.bookprint.controllers;

import com.ms.printing.bookprint.models.ShipmentTrackingDetails;
import com.ms.printing.bookprint.service.ShippingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "print-service", description = "The shipment operations APIs")
public class ShippingController {

    @Resource
    private ShippingService shippingService;

    @RequestMapping(value = "track/{shipmentId}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Get the shipment tracking details for the provided shipmentId")
    public ResponseEntity<ShipmentTrackingDetails> trackShipment(@ApiParam(name = "shipmentId", required = true) @PathVariable(value = "shipmentId") String shipmentId) {
        ShipmentTrackingDetails shipmentTrackingDetails = shippingService.trackShipment(shipmentId);
        return new ResponseEntity<>(shipmentTrackingDetails, HttpStatus.OK);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST", value = "Submits a new shipping order")
    public ResponseEntity<ShipmentTrackingDetails> addProduct(@RequestBody String order) {
        ShipmentTrackingDetails shipmentTrackingDetails = shippingService.submitOrder(order);
        return new ResponseEntity<>(shipmentTrackingDetails, HttpStatus.OK);
    }

}