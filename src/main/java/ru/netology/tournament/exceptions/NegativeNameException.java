package ru.netology.tournament.exceptions;

public class NegativeNameException extends RuntimeException {

    public NegativeNameException(String msg) {
        super(msg);
    }
}
