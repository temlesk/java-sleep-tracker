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

class SleeplessNightsTest {

    SleeplessNights sleeplessNights = new SleeplessNights();

    @DisplayName("Тестирование корректного количества бессоных ночей при их отсутствии в списке")
    @Test
    void sleeplessNightReturnCorrectCountTheyAreNotInTheList() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 1,23,0),
                LocalDateTime.of(2025, 1, 2,6,0),
                SleepQuality.NORMAL
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 3,22,0),
                LocalDateTime.of(2025, 1, 3,8,0),
                SleepQuality.NORMAL
        ));

        SleepAnalysisResult result = sleeplessNights.apply(sessions);
        assertEquals(SleeplessNights.TITLE, result.getFunctionTitle());
        assertEquals(1L, result.getResult());
    }

    @DisplayName("Тестирование корректного количества бессоных ночей")
    @Test
    void sleeplessNightReturnCorrectCountWithoutSession() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 1,17,0),
                LocalDateTime.of(2025, 1, 1,23,0),
                SleepQuality.NORMAL
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 3,6,0),
                LocalDateTime.of(2025, 1, 3,10,0),
                SleepQuality.NORMAL
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 6,22,0),
                LocalDateTime.of(2025, 1, 6,23,0),
                SleepQuality.NORMAL
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 7,23,0),
                LocalDateTime.of(2025, 1, 8,10,0),
                SleepQuality.NORMAL
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 8,22,0),
                LocalDateTime.of(2025, 1, 9,23,0),
                SleepQuality.NORMAL
        ));

        SleepAnalysisResult result = sleeplessNights.apply(sessions);
        assertEquals(SleeplessNights.TITLE, result.getFunctionTitle());
        assertEquals(2L, result.getResult());
    }
}