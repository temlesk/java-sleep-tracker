package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class MaxDurationSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    public static final String TITLE = "Максимальная продолжительность сессии (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Optional<Duration> maxDuration = sleepingSessions.stream()
                .filter(Objects::nonNull)
                .map(sleepingSession -> Duration.between(sleepingSession.start(), sleepingSession.end()))
                .max(Duration::compareTo);

        if (maxDuration.isPresent()) {
            long minutes = maxDuration.get().toMinutes();
            return new SleepAnalysisResult(TITLE, minutes);
        } else {
            return new SleepAnalysisResult(TITLE, "Максимальная продолжительность равна 0");
        }
    }
}
