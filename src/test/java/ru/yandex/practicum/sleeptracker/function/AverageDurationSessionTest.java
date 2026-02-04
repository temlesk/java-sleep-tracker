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

class AverageDurationSessionTest {

    AverageDurationSession averageDurationSession = new AverageDurationSession();

    @DisplayName("Тест нахождение средней продолжительности сессий")
    @Test
    void averageDurationSession() {
        List<SleepingSession> session = new ArrayList<>();

        session.add(new SleepingSession(
                LocalDateTime.of(2026, 5, 14, 21,0),
                LocalDateTime.of(2026, 5, 15, 5,30),
                SleepQuality.GOOD));

        session.add(new SleepingSession(
                LocalDateTime.of(2026, 9, 10, 23,0),
                LocalDateTime.of(2026, 9, 11, 5,30),
                SleepQuality.NORMAL));

        session.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 10, 23,0),
                LocalDateTime.of(2026, 1, 11, 10,30),
                SleepQuality.GOOD));

        session.add(new SleepingSession(
                LocalDateTime.of(2026, 9, 20, 23,0),
                LocalDateTime.of(2026, 9, 21, 12,30),
                SleepQuality.GOOD));

        SleepAnalysisResult result = averageDurationSession.apply(session);
        assertEquals(AverageDurationSession.TITLE, result.getFunctionTitle());
        assertEquals(600L, result.getResult());
    }

    @DisplayName("Тест нахождение средней продолжительности сессий при пустом списке")
    @Test
    void averageDurationSessionWithoutSession() {
        List<SleepingSession> sessions = new ArrayList<>();

        SleepAnalysisResult result = averageDurationSession.apply(sessions);
        assertEquals(AverageDurationSession.TITLE, result.getFunctionTitle());
        assertEquals("Не удалось найтри среднюю продолжительность сессии", result.getFunctionTitle());
    }
}