package com.example.demo.EventDrivenArchitecture;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentListener {
    @EventListener
    public void handleEnrollmentEvent(EnrollmentEvent event) {
        // Send email notification
    }
}
