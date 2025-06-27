package com.foodcourt.squaremallmanagment.domain.spi;

public interface ISendNotificationPort {

    void sendMessage(String toPhoneNumber, String messageBody);
}
