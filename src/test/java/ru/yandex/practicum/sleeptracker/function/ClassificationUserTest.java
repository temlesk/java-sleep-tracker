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

class ClassificationUserTest {

    ClassificationUser classificationUser = new ClassificationUser();

    @DisplayName("Тест, когда кол-во типов равное")
    @Test
    void apply() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0,0),
                LocalDateTime.of(2026, 1, 1, 10,0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 21,0),
                LocalDateTime.of(2026, 1, 3, 6,0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 23,0),
                LocalDateTime.of(2026, 1, 4, 6,0),
                SleepQuality.GOOD
        ));

        SleepAnalysisResult result = classificationUser.apply(sessions);

        assertEquals(ClassificationUser.TITLE, result.getFunctionTitle());
        assertEquals("Голубь", result.getResult());
    }

    @DisplayName("Тест, когда сессия сна без перехода дня для Совы")
    @Test
    void testOwl() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0,0),
                LocalDateTime.of(2026, 1, 1, 10,0),
                SleepQuality.GOOD
        ));

        SleepAnalysisResult result = classificationUser.apply(sessions);

        assertEquals(ClassificationUser.TITLE, result.getFunctionTitle());
        assertEquals("Сова", result.getResult());
    }

    @DisplayName("Тест, с переходом на другой день для совы")
    @Test
    void testOwlPrevDay() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 23,30),
                LocalDateTime.of(2026, 1, 2, 10,0),
                SleepQuality.GOOD
        ));

        SleepAnalysisResult result = classificationUser.apply(sessions);

        assertEquals(ClassificationUser.TITLE, result.getFunctionTitle());
        assertEquals("Сова", result.getResult());
    }

    @DisplayName("Тест, когда сессий с классификацией -Сова- больше")
    @Test
    void countOwlBigger() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 23,30),
                LocalDateTime.of(2026, 1, 2, 10,0),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 5, 2,0),
                LocalDateTime.of(2026, 1, 5, 12,0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 21,0),
                LocalDateTime.of(2026, 1, 3, 6,0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 23,0),
                LocalDateTime.of(2026, 1, 4, 6,0),
                SleepQuality.GOOD
        ));

        SleepAnalysisResult result = classificationUser.apply(sessions);

        assertEquals(ClassificationUser.TITLE, result.getFunctionTitle());
        assertEquals("Сова", result.getResult());
    }
}