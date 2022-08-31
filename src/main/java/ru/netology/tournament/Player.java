package ru.netology.tournament;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player {
    private int id;
    private String name;
    private int strength;
}
