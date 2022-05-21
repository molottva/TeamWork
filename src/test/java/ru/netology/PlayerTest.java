package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }


    @Test
    public void TestSumGenreTwoGames() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Ведьмак", "Рпг");
        Game game1 = store.publishGame("Ведьмак 2", "Рпг");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game3);
        player.play(game, 3);
        player.play(game1, 2);
        player.play(game3, 1);

        int expected = 5;
        int actual = player.sumGenre("Рпг");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRunTimeException() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Ведьмак", "Рпг");
        Game game1 = store.publishGame("Ведьмак 2", "Рпг");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);


        assertThrows(RuntimeException.class, () -> {
            player.play(game3, 1);

        });
    }

    @Test
    public void Test3() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Ведьмак", "Рпг");
        Game game1 = store.publishGame("Ведьмак 2", "Рпг");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game3);
        player.play(game, 3);
        player.play(game1, 2);
        player.play(game3, 1);

        String expected = game.getTitle();
        Game actual = player.mostPlayerByGenre("Рпг");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Ведьмак", "Рпг");
        Game game1 = store.publishGame("Ведьмак 2", "Рпг");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.play(game, 3);
        player.play(game1, 2);


        String expected = null;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }


}
