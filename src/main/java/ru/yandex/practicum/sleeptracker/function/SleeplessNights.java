package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {

    public static final String TITLE = "Выявление бессоных ночей";
    public static final LocalTime END_SLEEP = LocalTime.of(6,0);

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult(
                TITLE,
                sleepingSessions.stream()
                    .filter(this::isSleeplessNight)
                    .count()
                );
    }

    private boolean isSleeplessNight(SleepingSession sleepingSession) {
        if (sleepingSession.start().toLocalDate().isBefore(sleepingSession.end().toLocalDate())) {
            return false;
        }
        LocalTime start = sleepingSession.start().toLocalTime();
        return start.isAfter(END_SLEEP);
    }
}