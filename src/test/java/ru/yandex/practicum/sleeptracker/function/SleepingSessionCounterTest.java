package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepQuality;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleepingSessionCounterTest {

    SleepingSessionCounter counter = new SleepingSessionCounter();

    @DisplayName("Тест путого списка сессий")
    @Test
    void sessionsEmpty() {
        List<SleepingSession> sessions = new ArrayList<>();

        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals("Количество сессий сна", result.getFunctionTitle());
        assertEquals(0, result.getResult());
    }

    @DisplayName("Тест не путого списка сессий")
    @Test
    void sessionsIsNoEmpty() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now().minusHours(8), LocalDateTime.now(), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now().minusHours(8), LocalDateTime.now(), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals("Количество сессий сна", result.getFunctionTitle());
        assertEquals(2, result.getResult());
    }
}