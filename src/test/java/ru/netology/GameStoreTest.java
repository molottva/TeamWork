package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStoreTest {
    @Nested
    @DisplayName("Тесты на список игр")
    public class GameListTest {
        GameStore storeEmpty = new GameStore();
        GameStore storeOneGame = new GameStore();
        GameStore storeSomeGames = new GameStore();

        @BeforeEach
        public void setup() {
            storeOneGame.publishGame("Witcher 3", "Action");
            storeSomeGames.publishGame("Witcher 3", "Action");
            storeSomeGames.publishGame("The Elder Scrolls V: Skyrim", "Action");
            storeSomeGames.publishGame("Kerbal Space Program", "Sandbox");
            storeSomeGames.publishGame("Europe Universalis IV", "Strategy");
            storeSomeGames.publishGame("XCOM 2", "Tactic");
        }

        @Disabled("Невозможно получить actual")
        @Nested
        @DisplayName("Тестирование метода publishGame()")
        public class publishGameTests {
            //TODO для тестабилити return в метод publishGame или геттер на games
            @Nested
            @DisplayName("Пустой магазин")
            public class EmptyStore {
                @Test
                public void shouldPublishGame() {
                    List<Game> expected = new ArrayList<>();
                    expected.add(new Game("Witcher 3", "Action", storeEmpty));
                    //assertTrue(expected.equals(storeEmpty.publishGame("Witcher 3", "Action")));
                }
            }

            @Nested
            @DisplayName("Магазин с одной игрой")
            public class StoreWitnOneGame {
                @Test
                public void shouldPublishNewGame() {
                    List<Game> expected = new ArrayList<>();
                    expected.add(new Game("Witcher 3", "Action", storeOneGame));
                    expected.add(new Game("Kerbal Space Program", "Sandbox", storeOneGame));
                    //assertTrue(expected.equals(storeOneGame.publishGame("Kerbal Space Program", "Sandbox")));
                }

                @Test
                public void shouldPublishOldGame() {
                    List<Game> expected = new ArrayList<>();
                    expected.add(new Game("Witcher 3", "Action", storeOneGame));
                    //assertTrue(expected.equals(storeOneGame.publishGame("Witcher 3", "Action")));
                }
            }

            @Nested
            @DisplayName("Магазин с несколькими играми")
            public class StoreWithSomeGames {
                @Test
                public void shouldPublishNewGame() {
                    List<Game> expected = new ArrayList<>();
                    expected.add(new Game("Witcher 3", "Action", storeSomeGames));
                    expected.add(new Game("The Elder Scrolls V: Skyrim", "Action", storeSomeGames));
                    expected.add(new Game("Kerbal Space Program", "Sandbox", storeSomeGames));
                    expected.add(new Game("Europe Universalis IV", "Strategy", storeSomeGames));
                    expected.add(new Game("XCOM 2", "Tactic", storeSomeGames));
                    expected.add(new Game("Grand Theft Auto V", "Action", storeSomeGames));
                    //assertTrue(expected.equals(storeSomeGames.publishGame("Grand Theft Auto V", "Action")));
                }

                @Test
                public void shouldPublishOldGame() {
                    List<Game> expected = new ArrayList<>();
                    expected.add(new Game("Witcher 3", "Action", storeSomeGames));
                    expected.add(new Game("The Elder Scrolls V: Skyrim", "Action", storeSomeGames));
                    expected.add(new Game("Kerbal Space Program", "Sandbox", storeSomeGames));
                    expected.add(new Game("Europe Universalis IV", "Strategy", storeSomeGames));
                    expected.add(new Game("XCOM 2", "Tactic", storeSomeGames));
                    assertTrue(expected.equals(storeSomeGames.publishGame("Europe Universalis IV", "Strategy")));
                }
            }
        }

        @Nested
        @DisplayName("Тестирование метода containsGame()")
        public class containsGameTests {
            @Nested
            @DisplayName("Пустой магазин")
            public class EmptyStore {
                @Test
                public void shouldUncontained() {
                    assertFalse(storeEmpty.containsGame(new Game("Witcher 3", "Action", storeEmpty)));
                }
            }

            @Nested
            @DisplayName("Магазин с одной игрой")
            public class StoreWitnOneGame {
                @Test
                public void shouldContained() {
                    assertTrue(storeOneGame.containsGame(new Game("Witcher 3", "Action", storeOneGame)));
                }

                @Test
                public void shouldUncontained() {
                    assertFalse(storeOneGame.containsGame(new Game("Kerbal Space Program", "Sandbox", storeOneGame)));
                }
            }

            @Nested
            @DisplayName("Магазин с несколькими играми")
            public class StoreWithSomeGames {
                @Test
                public void shouldContained() {
                    assertTrue(storeSomeGames.containsGame(new Game("Kerbal Space Program", "Sandbox", storeSomeGames)));
                }

                @Test
                public void shouldUncontained() {
                    assertFalse(storeSomeGames.containsGame(new Game("Factorio", "Sandbox", storeSomeGames)));
                }
            }
        }
    }

    @Nested
    @DisplayName("Тесты на кол-во игрового времени")
    public class PlayTimeTests {
        GameStore storeEmpty = new GameStore();
        GameStore storeOnePlayer = new GameStore();
        GameStore storeSomePlayers = new GameStore();

        @BeforeEach
        public void setup() {
            storeOnePlayer.addPlayTime("Кодзима-Гений", 60);
            storeSomePlayers.addPlayTime("Кодзима-Гений", 60);
            storeSomePlayers.addPlayTime("Виталий", 50);
            storeSomePlayers.addPlayTime("Никита", 40);
            storeSomePlayers.addPlayTime("Юрий", 30);
            storeSomePlayers.addPlayTime("Иван", 20);
        }

        @Disabled("Невозможно получить actual")
        @Nested
        @DisplayName("Тестирование метода addPlayTime()")
        public class AddPlayTimeTests {
            //TODO для тестабилити сделать return в методе addPlayTime() или геттер на playedTime
            @Nested
            @DisplayName("Магазин без игроков")
            public class StoreEmptyTest {

                @Test
                public void shouldAddPlayTime() {
                    Map<String, Integer> expected = new HashMap<>();
                    expected.put("Кодзима-Гений", 10);

                    //assertTrue(expected.equals(storeEmpty.addPlayTime("Кодзима-Гений", 10)));
                }
            }

            @Nested
            @DisplayName("Один игрок")
            public class StoreOnePlayerTests {

                @Test
                public void shouldAddPlayTimeNewPlayer() {
                    Map<String, Integer> expected = new HashMap<>();
                    expected.put("Кодзима-Гений", 60);
                    expected.put("Никита", 10);

                    //assertTrue(expected.equals(storeOnePlayer.addPlayTime("Никита", 10)));
                }

                @Test
                public void shouldAddPlayTimeOneOldPlayer() {
                    Map<String, Integer> expected = new HashMap<>();
                    expected.put("Кодзима-Гений", 70);

                    //assertTrue(expected.equals(storeOnePlayer.addPlayTime("Кодзима-Гений", 10)));
                }
            }

            @Nested
            @DisplayName("Несколько игроков")
            public class StoreSomePlayerTests {
                @Test
                public void shouldAddPlayTimeNewPlayer() {
                    Map<String, Integer> expected = new HashMap<>();
                    expected.put("Кодзима-Гений", 60);
                    expected.put("Виталий", 50);
                    expected.put("Никита", 40);
                    expected.put("Юрий", 30);
                    expected.put("Иван", 20);
                    expected.put("Владислав", 10);
                    storeSomePlayers.addPlayTime("Кодзима-Гений", 60);
                    storeSomePlayers.addPlayTime("Виталий", 50);
                    storeSomePlayers.addPlayTime("Никита", 40);
                    storeSomePlayers.addPlayTime("Юрий", 30);
                    storeSomePlayers.addPlayTime("Иван", 20);

                    //assertTrue(expected.equals(storeOnePlayer.addPlayTime("Владислав", 10)));
                }

                @Test
                public void shouldAddPlayTimeOneOldPlayer() {
                    Map<String, Integer> expected = new HashMap<>();
                    expected.put("Кодзима-Гений", 60);
                    expected.put("Виталий", 50);
                    expected.put("Никита", 60);
                    expected.put("Юрий", 30);
                    expected.put("Иван", 20);

                    //assertTrue(expected.equals(storeOnePlayer.addPlayTime("Никита", 20)));
                }
            }
        }

        @Nested
        @DisplayName("Тесты на метод getMostPlayer")
        public class GetMostPlayerTests {
            @Nested
            @DisplayName("Магазин без игроков")
            public class StoreEmptyTest {

                @Test
                public void shouldGetMostPlayerNull() {
                    assertNull(storeEmpty.getMostPlayer());
                }
            }

            @Nested
            @DisplayName("Один игрок")
            public class StoreOnePlayerTests {
                @Test
                public void shouldGetMostPlayer() {
                    String expected = "Кодзима-Гений";
                    assertTrue(expected.equals(storeOnePlayer.getMostPlayer()));
                }
            }

            @Nested
            @DisplayName("Несколько игроков")
            public class StoreSomePlayerTests {
                @Test
                public void shouldGetOneMostPlayer() {
                    String expected = "Кодзима-Гений";
                    assertTrue(expected.equals(storeSomePlayers.getMostPlayer()));
                }

                @Disabled
                @Test
                public void shouldGetManyMostPlayer() {
                    String[] expected = new String[]{"Кодзима-Гений", "Анна"};
                    storeSomePlayers.addPlayTime("Анна", 60);
                    assertTrue(expected.equals(storeSomePlayers.getMostPlayer()));
                }
            }
        }

        @Nested
        @DisplayName("Тесты на метод getSumPlayedTime()")
        public class GetSumPlayedTimeTests {
            @Nested
            @DisplayName("Магазин без игроков")
            public class StoreEmptyTest {

                @Test
                public void shouldGetSumPlayedTime() {
                    int expected = 0;
                    assertEquals(expected, storeEmpty.getSumPlayedTime());
                }
            }

            @Nested
            @DisplayName("Один игрок")
            public class StoreOnePlayerTests {
                @Test
                public void shouldGetSumPlayedTime() {
                    int expected = 60;
                    assertEquals(expected, storeOnePlayer.getSumPlayedTime());
                }
            }

            @Nested
            @DisplayName("Несколько игроков")
            public class StoreSomePlayerTests {
                @Test
                public void shouldGetSumPlayedTime() {
                    int expected = 200;
                    assertEquals(expected, storeSomePlayers.getSumPlayedTime());
                }
            }
        }
    }
}
