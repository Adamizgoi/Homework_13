package ru.netology.tournament.exceptions;

public class NotRegisteredException extends RuntimeException {

    public NotRegisteredException(String msg) {
        super(msg);
    }
}
