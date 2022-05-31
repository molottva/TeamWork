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

        //        @Disabled("Невозможно получить actual")
        @Nested
        @DisplayName("Тестирование метода publishGame()")
        public class publishGameTests {
            @Nested
            @DisplayName("Пустой магазин")
            public class EmptyStore {
                @Test
                public void shouldPublishGame() {
                    List<Game> expected = new ArrayList<>();
                    expected.add(new Game("Witcher 3", "Action", storeEmpty));
                    storeEmpty.publishGame("Witcher 3", "Action");
                    assertEquals(expected, storeEmpty.getGames());
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
                    storeOneGame.publishGame("Kerbal Space Program", "Sandbox");
                    assertEquals(expected, storeOneGame.getGames());
                }

                @Test
                public void shouldPublishOldGame() {
                    //todo bug
                    List<Game> expected = new ArrayList<>();
                    expected.add(new Game("Witcher 3", "Action", storeOneGame));
                    storeOneGame.publishGame("Witcher 3", "Action");
                    assertEquals(expected, storeOneGame.getGames());
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
                    storeSomeGames.publishGame("Grand Theft Auto V", "Action");
                    assertEquals(expected, storeSomeGames.getGames());
                }

                @Test
                public void shouldPublishOldGame() {
                    List<Game> expected = new ArrayList<>();
                    expected.add(new Game("Witcher 3", "Action", storeSomeGames));
                    expected.add(new Game("The Elder Scrolls V: Skyrim", "Action", storeSomeGames));
                    expected.add(new Game("Kerbal Space Program", "Sandbox", storeSomeGames));
                    expected.add(new Game("Europe Universalis IV", "Strategy", storeSomeGames));
                    expected.add(new Game("XCOM 2", "Tactic", storeSomeGames));
                    storeSomeGames.publishGame("Europe Universalis IV", "Strategy");
                    assertEquals(expected, storeSomeGames.getGames());
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

        //        @Disabled("Невозможно получить actual")
        @Nested
        @DisplayName("Тестирование метода addPlayTime()")
        public class AddPlayTimeTests {
            @Nested
            @DisplayName("Магазин без игроков")
            public class StoreEmptyTest {

                @Test
                public void shouldAddPlayTime() {
                    Map<String, Integer> expected = new HashMap<>();
                    expected.put("Кодзима-Гений", 10);
                    storeEmpty.addPlayTime("Кодзима-Гений", 10);
                    assertEquals(expected, storeEmpty.getPlayedTime());
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
                    storeOnePlayer.addPlayTime("Никита", 10);
                    assertEquals(expected, storeOnePlayer.getPlayedTime());
                }

                @Test
                public void shouldAddPlayTimeOneOldPlayer() {
                    Map<String, Integer> expected = new HashMap<>();
                    expected.put("Кодзима-Гений", 70);
                    storeOnePlayer.addPlayTime("Кодзима-Гений", 10);
                    assertEquals(expected, storeOnePlayer.getPlayedTime());
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
                    storeSomePlayers.addPlayTime("Владислав", 10);
                    assertEquals(expected, storeSomePlayers.getPlayedTime());
                }

                @Test
                public void shouldAddPlayTimeOneOldPlayer() {
                    Map<String, Integer> expected = new HashMap<>();
                    expected.put("Кодзима-Гений", 60);
                    expected.put("Виталий", 50);
                    expected.put("Никита", 60);
                    expected.put("Юрий", 30);
                    expected.put("Иван", 20);
                    storeSomePlayers.addPlayTime("Никита", 20);
                    assertEquals(expected, storeSomePlayers.getPlayedTime());
                }

                @Test
                public void shouldAddPlayTimeExceptionNegativeValue() {
                    assertThrows(RuntimeException.class, () -> {
                        storeSomePlayers.addPlayTime("Юлия", -5);
                    });
                }

                @Test
                public void shouldAddPlayTimeExceptionZeroValue() {
                    assertThrows(RuntimeException.class, () -> {
                        storeSomePlayers.addPlayTime("Никита", 0);
                    });
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
                    String[] expected = new String[]{"Кодзима-Гений"};
                    assertArrayEquals(expected, storeOnePlayer.getMostPlayer());
                }
            }

            @Nested
            @DisplayName("Несколько игроков")
            public class StoreSomePlayerTests {
                @Test
                public void shouldGetOneMostPlayer() {
                    String[] expected = new String[]{"Кодзима-Гений"};
                    assertArrayEquals(expected, storeSomePlayers.getMostPlayer());
                }

                @Test
                public void shouldGetManyMostPlayer() {
                    String[] expected = new String[]{"Кодзима-Гений", "Анна"};
                    storeSomePlayers.addPlayTime("Анна", 60);
                    assertArrayEquals(expected, storeSomePlayers.getMostPlayer());
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
