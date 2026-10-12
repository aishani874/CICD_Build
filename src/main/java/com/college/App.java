package com.college;

public class App {
    public static String getGreeting() {
        return "Lab 13: CI/CD Pipeline Build Successful!";
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getGreeting());
        System.out.println("Application is running inside Docker container...");
        // Keep container running to demonstrate status
        while (true) {
            Thread.sleep(10000);
        }
    }
}