package ru.netology.tournament.managers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.tournament.Player;
import ru.netology.tournament.exceptions.NegativeIdException;
import ru.netology.tournament.exceptions.NegativeNameException;
import ru.netology.tournament.exceptions.NotRegisteredException;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    Game gameManager = new Game();
    List<Player> testPlayers = new ArrayList<>();

    Player player1 = new Player(1, "moon1", 1000);
    Player player2 = new Player(2, "FALLenAngel_201", 999);
    Player player3error = new Player(1, "fire.BOY", 2000);
    Player player4 = new Player(4, "moon1fighter", 1001);
    Player player5 = new Player(5, "seP00rator", 1000);
    Player player6error = new Player(6, "moon1", 1231);
    Player player7error = new Player(-7, "57733", 3456);
    Player player8error = new Player(8, "___", -1);

    @Test
    public void shouldFindWinnerBetweenTwoPlayersIfFirstHaveMoreStrength() {
        gameManager.register(player1);
        gameManager.register(player2);

        String expected = "Победил игрок " + player1.getName();
        String actual = gameManager.roundResult("moon1", "FALLenAngel_201");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindWinnerBetweenTwoPlayersIfSecondHaveMoreStrength() {
        gameManager.register(player1);
        gameManager.register(player4);

        String expected = "Победил игрок " + player4.getName();
        String actual = gameManager.roundResult("moon1", "moon1fighter");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindWinnerBetweenTwoPlayersWithEqualStrength() {
        gameManager.register(player1);
        gameManager.register(player5);

        String expected = "Ничья!";
        String actual = gameManager.roundResult("moon1", "seP00rator");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowNegativeIDExceptionIfRegisterPlayerWithIDAlreadyUsed() {
        gameManager.register(player1);

        Assertions.assertThrows(NegativeIdException.class, () -> {
            gameManager.register(player3error);
        });
    }

    @Test
    public void shouldShowNegativeNameExceptionIfRegisterPlayerWithNameAlreadyUsed() {
        gameManager.register(player1);

        Assertions.assertThrows(NegativeNameException.class, () -> {
            gameManager.register(player6error);
        });
    }

    @Test
    public void shouldShowNegativeIdExceptionIfRegisterPlayerWithLessZeroId() {
        Assertions.assertThrows(NegativeIdException.class, () -> {
            gameManager.register(player7error);;
        });
    }

    @Test
    public void shouldShowNotRegisteredExceptionIfFirstRoundPlayerIsNotRegistred() {
        gameManager.register(player1);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            gameManager.roundResult("moon1", "FALLenAngel_201");
        });
    }

    @Test
    public void shouldShowNotRegisteredExceptionIfSecondRoundPlayerIsNotRegistred() {
        gameManager.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            gameManager.roundResult("moon1", "FALLenAngel_201");
        });
    }

    @Test
    public void shouldNotRegisterPlayerWithWithStrengthLessZero() {
        gameManager.register(player1);
        gameManager.register(player8error);
        testPlayers.add(player1);

        List<Player> expected = testPlayers;
        List<Player> actual = gameManager.getPlayers();

        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
