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

class MinDurationSessionTest {

    MinDurationSession durationSession = new MinDurationSession();

    @DisplayName("Тест нахождения минимальной сессии сна из двух")
    @Test
    void minDurationWithTwoSession() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 25, 23, 0),
                LocalDateTime.of(2025, 10, 26, 7, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 25, 0, 0),
                LocalDateTime.of(2025, 10, 25, 7, 0),
                SleepQuality.GOOD
        ));

        SleepAnalysisResult result = durationSession.apply(sessions);
        assertEquals(MinDurationSession.TITLE, result.getFunctionTitle());
        assertEquals(420L, result.getResult());
    }

    @DisplayName("Тест нахождения минимальной сессии при пустом списке")
    @Test
    void minDurationWithoutSession() {
        List<SleepingSession> sessions = new ArrayList<>();

        SleepAnalysisResult result = durationSession.apply(sessions);
        assertEquals(MinDurationSession.TITLE, result.getFunctionTitle());
        assertEquals("Минимальная продолжительность равна 0", result.getResult());
    }
}