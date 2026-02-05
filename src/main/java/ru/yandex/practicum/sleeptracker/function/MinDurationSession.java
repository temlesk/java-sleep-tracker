package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MinDurationSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    public static final String TITLE = "Минимальная продолжительность сессии (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Optional<Duration> minDuration = sleepingSessions.stream()
                .map(sleepingSession -> Duration.between(sleepingSession.start(), sleepingSession.end()))
                .min(Duration::compareTo);

        if (minDuration.isPresent()) {
            long minutes = minDuration.get().toMinutes();
            return new SleepAnalysisResult(TITLE, minutes);
        } else {
            return new SleepAnalysisResult(TITLE, "Минимальная продолжительность равна 0");
        }
    }
}
