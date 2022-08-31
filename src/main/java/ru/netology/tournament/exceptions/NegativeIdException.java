package ru.netology.tournament.exceptions;

public class NegativeIdException extends RuntimeException {

    public NegativeIdException(String msg) {
        super(msg);
    }
}
