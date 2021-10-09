package com.ms.printing.bookprint.models;

import com.ms.printing.bookprint.enums.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShipmentTrackingDetails {
    private UUID shipmentId;
    private UUID orderId;
    private String currentLocation;
    private ShippingStatus status;
}
