package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepQuality;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class QuantitySessionWithBadSleepQuality implements Function<List<SleepingSession>, SleepAnalysisResult> {

    public static final String TITLE = "Количество сессий с плохим качеством сна";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult(
                TITLE,
                sleepingSessions.stream()
                        .filter(Objects::nonNull)
                        .filter(session -> session.quality() == SleepQuality.BAD)
                        .count()

        );
    }
}
