package com.ms.printing.bookprint.service;

import com.ms.printing.bookprint.models.ShipmentTrackingDetails;

public interface ShippingService {

    ShipmentTrackingDetails trackShipment(String shipmentId);

    ShipmentTrackingDetails submitOrder(String order);
}
