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

class MaxDurationSessionTest {

    MaxDurationSession maxDurationSession = new MaxDurationSession();

    @DisplayName("Тест нахождение максимальной продолжительности сессии сна (в минутах)")
    @Test
    void maxDurationWithTwoSession() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1,11, 22,0),
                LocalDateTime.of(2025, 1, 12, 9,0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 12, 20, 1, 30),
                LocalDateTime.of(2025, 12, 20, 6, 55),
                SleepQuality.BAD
        ));

        SleepAnalysisResult result = maxDurationSession.apply(sessions);
        assertEquals(MaxDurationSession.TITLE, result.getFunctionTitle());
        assertEquals(660L, result.getResult());
    }

    @DisplayName("Тест нахождения максимальной продолжительности сесси сна при пустом списке")
    @Test
    void maxDurationWithoutSession() {
        List<SleepingSession> sessions = new ArrayList<>();

        SleepAnalysisResult result = maxDurationSession.apply(sessions);
        assertEquals(MaxDurationSession.TITLE, result.getFunctionTitle());
        assertEquals("Максимальная продолжительность равна 0", result.getResult());
    }
}