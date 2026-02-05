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

class QuantitySessionWithBadSleepQualityTest {

    QuantitySessionWithBadSleepQuality quality = new QuantitySessionWithBadSleepQuality();

    @DisplayName("Тест нахождения количества сессий с плохим качеством сна")
    @Test
    void badSleepQuality() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 12, 23, 23, 0),
                LocalDateTime.of(2025, 12, 24, 5, 0),
                SleepQuality.BAD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 11, 23, 23, 0),
                LocalDateTime.of(2026, 11, 24, 5, 0),
                SleepQuality.BAD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 11, 1, 23, 0),
                LocalDateTime.of(2025, 11, 1, 7, 0),
                SleepQuality.NORMAL
        ));

        SleepAnalysisResult result = quality.apply(sessions);
        assertEquals(QuantitySessionWithBadSleepQuality.TITLE, result.getFunctionTitle());
        assertEquals(2L, result.getResult());
    }

    @DisplayName("Тест нахождения количества сессий с плохим качеством сна")
    @Test
    void badSleepQualityWithoutSession() {
        List<SleepingSession> sessions = new ArrayList<>();

        SleepAnalysisResult result = quality.apply(sessions);
        assertEquals(QuantitySessionWithBadSleepQuality.TITLE, result.getFunctionTitle());
        assertEquals(0L, result.getResult());
    }

}