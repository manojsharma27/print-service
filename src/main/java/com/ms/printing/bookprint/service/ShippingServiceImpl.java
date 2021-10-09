package com.ms.printing.bookprint.service;

import com.ms.printing.bookprint.enums.ShippingStatus;
import com.ms.printing.bookprint.models.ShipmentTrackingDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class ShippingServiceImpl implements ShippingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingServiceImpl.class);
    private ShipmentTrackingDetails mockDetails;

    @PostConstruct
    public void init() {
        mockDetails = ShipmentTrackingDetails.builder()
                .orderId(UUID.randomUUID())
                .shipmentId(UUID.randomUUID())
                .status(ShippingStatus.DISPATCHED)
                .currentLocation("Mumbai")
                .build();
    }

    @Override
    public ShipmentTrackingDetails trackShipment(String shipmentId) {
        return mockDetails;
    }

    @Override
    public ShipmentTrackingDetails submitOrder(String order) {
        return mockDetails;
    }
}