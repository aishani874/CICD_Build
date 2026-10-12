package com.college;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void testGreeting() {
        assertEquals("Lab 13: CI/CD Pipeline Build Successful!", App.getGreeting());
    }
}