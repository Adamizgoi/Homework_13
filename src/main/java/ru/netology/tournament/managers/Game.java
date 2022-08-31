package ru.netology.tournament.managers;

import lombok.Getter;
import ru.netology.tournament.Player;
import ru.netology.tournament.comparators.PlayerStrengthComparator;
import ru.netology.tournament.exceptions.NegativeIdException;
import ru.netology.tournament.exceptions.NegativeNameException;
import ru.netology.tournament.exceptions.NotRegisteredException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
// ТУТ НУЖЕН ОЛ АРГС КОНСТРАКТОР??
public class Game {
    private List<Player> players = new ArrayList<>();

    public void register(Player newPlayer) {
        // метод регистрации игрока
        if (registerCheck(newPlayer)) {
            players.add(newPlayer);
        }
    }

    private boolean registerCheck(Player somePlayer) {
        if (somePlayer.getId() < 0) {
            throw new NegativeIdException(
                    "ID не может быть отрицательным числом: " + somePlayer.getId()
            );
        }
        if (somePlayer.getStrength() < 0) {
            return false;
        }
        for (Player randomPlayer : players) {
            if (somePlayer.getId() == randomPlayer.getId()) {
                throw new NegativeIdException(
                        "Такой ID уже используется: " + somePlayer.getId()
                );
            }
            if (somePlayer.getName() == randomPlayer.getName()) {
                throw new NegativeNameException(
                        "Такое имя пользователя уже используется: " + somePlayer.getName()
                );
            }
        }
        return true;
    }

    public String roundResult(String playerName1, String playerName2) {
        int result = round(playerName1, playerName2);
        String publicResult;

        if (result > 0) {
            publicResult = "Победил игрок " + playerName1;
        } else if (result < 0) {
            publicResult = "Победил игрок " + playerName2;
        } else {
            publicResult = "Ничья!";
        }
        return publicResult;
    }

    private int round(String playerName1, String playerName2) {
        PlayerStrengthComparator strengthComparator = new PlayerStrengthComparator();
        Player player1 = null;
        Player player2 = null;

        for (Player randomPlayer : players) {
            if (randomPlayer.getName() == playerName1) {
                player1 = randomPlayer;
            }
            if (randomPlayer.getName() == playerName2) {
                player2 = randomPlayer;
            }
        }

        if (player1 == null) {
            throw new NotRegisteredException(
                    "Игрок не зарегистрирован в турнире: " + playerName1
            );
        } else if (player2 == null) {
            throw new NotRegisteredException(
                    "Игрок не зарегистрирован в турнире: " + playerName2
            );
        } else {
            return strengthComparator.compare(player1, player2);
        }
    }
}
