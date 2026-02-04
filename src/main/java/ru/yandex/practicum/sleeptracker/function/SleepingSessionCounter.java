package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class SleepingSessionCounter implements Function <List<SleepingSession> , SleepAnalysisResult> {

    public static final String TITLE = "Количество сессий сна";

    @Override
    public SleepAnalysisResult apply(final List<SleepingSession> sleepingSessions) {
        Integer quanity = sleepingSessions.size();
        return new SleepAnalysisResult(TITLE, quanity);
    }
}