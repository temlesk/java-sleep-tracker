package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AverageDurationSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    public static final String TITLE = "Средняя продолжительность сессии (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        OptionalDouble averageDuration = sleepingSessions.stream()
                .filter(Objects::nonNull)
                .map(sleepingSession -> Duration.between(sleepingSession.start(), sleepingSession.end()))
                .mapToLong(Duration::toMinutes)
                .average();

        if (averageDuration.isPresent()){
            long average = Math.round(averageDuration.getAsDouble());
            return new SleepAnalysisResult(TITLE ,average);
        } else {
            return new SleepAnalysisResult(TITLE, "Не удалось найти среднюю продолжительность сессии");
        }
    }
}
