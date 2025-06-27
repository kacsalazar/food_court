package com.foodcourt.squaremallmanagment.domain.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NotificationOrderModel {
    private String phoneNumber;
    private String messageBody;
}
