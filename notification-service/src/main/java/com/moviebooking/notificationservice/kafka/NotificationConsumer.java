package com.moviebooking.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {


    @KafkaListener(
            topics = "booking-confirmed",
            groupId = "notification-group"
    )
    public void consume(String message) {

        System.out.println("==================================");
        System.out.println("Booking Event Received");
        System.out.println(message);
        System.out.println("Sending Email Notification...");
        System.out.println("Sending SMS Notification...");
        System.out.println("Notification Sent Successfully");
        System.out.println("==================================");
    }
}
