package ru.netology.delivery.data;

import ru.netology.delivery.data.DataGenerator.Registration;

public class Main {
    public static void main(String[] args) {
        var validUser = Registration.generateUser();
        System.out.println(validUser);
    }
}
