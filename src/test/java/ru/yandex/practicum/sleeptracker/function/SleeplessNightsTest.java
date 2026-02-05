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

    @DisplayName("Тестирование одной бессоной ночи из 3 ночей")
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
                LocalDateTime.of(2025, 1, 4,8,0),
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
                LocalDateTime.of(2025, 1, 1,7,0),
                LocalDateTime.of(2025, 1, 1,11,0),
                SleepQuality.NORMAL
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 3,17,0),
                LocalDateTime.of(2025, 1, 3,23,0),
                SleepQuality.NORMAL
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 5,0,0),
                LocalDateTime.of(2025, 1, 6,8,0),
                SleepQuality.NORMAL
        ));

        SleepAnalysisResult result = sleeplessNights.apply(sessions);
        assertEquals(SleeplessNights.TITLE, result.getFunctionTitle());
        assertEquals(4L, result.getResult());
    }

    @Test
    void sleeplessNight19to5() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 29,23,0),
                LocalDateTime.of(2025, 1, 30,1,0),
                SleepQuality.NORMAL
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 2, 4,7,0),
                LocalDateTime.of(2025, 2, 4,23,0),
                SleepQuality.NORMAL
        ));

        SleepAnalysisResult result = sleeplessNights.apply(sessions);
        assertEquals(SleeplessNights.TITLE, result.getFunctionTitle());
        assertEquals(5L, result.getResult());
    }

    @Test
    void sleeplessNight1to2() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 1, 1,1,0),
                LocalDateTime.of(2025, 1, 1,2,0),
                SleepQuality.NORMAL
        ));

        SleepAnalysisResult result = sleeplessNights.apply(sessions);
        assertEquals(SleeplessNights.TITLE, result.getFunctionTitle());
        assertEquals(0L, result.getResult());
    }
}