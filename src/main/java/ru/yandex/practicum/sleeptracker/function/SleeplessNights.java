package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class SleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {

    public static final String TITLE = "Выявление бессоных ночей";
    public static final LocalTime BORDER_OF_THE_DAY = LocalTime.of(12,0);
    public static final LocalTime START_SLEEP = LocalTime.of(0,0);
    public static final LocalTime END_SLEEP = LocalTime.of(6,0);

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        List<LocalDate> nights = createNights(sleepingSessions);
        return new SleepAnalysisResult(
                TITLE,
                nights.stream()
                    .filter(night -> isSleeplessNight(night, sleepingSessions))
                    .count()
                );
    }

    private List<LocalDate> createNights(List<SleepingSession> sleepingSessions) {
        LocalDate nightsStartedAt = getSessionNight(sleepingSessions.getFirst());
        LocalDate nightsFinishedAt = getSessionNight(sleepingSessions.getLast());

        int nightsToAnalyze = Period.between(nightsStartedAt, nightsFinishedAt.plusDays(1))
                .getDays();

        return IntStream.range(0, nightsToAnalyze)
                .mapToObj(nightsStartedAt::plusDays).toList();
    }

    private LocalDate getSessionNight(SleepingSession session) {
        return session.start().toLocalTime().isAfter(BORDER_OF_THE_DAY)
                ? session.start().toLocalDate().plusDays(1)
                : session.start().toLocalDate();
    }

    private boolean isSleeplessNight(LocalDate night, List<SleepingSession> sleepingSessions) {
        return sleepingSessions.stream().noneMatch(session -> isSleepSessionFor(night, session));
    }

    private static boolean isSleepSessionFor(LocalDate night, SleepingSession session) {
        LocalDateTime start = night.atTime(START_SLEEP);
        LocalDateTime end = night.atTime(END_SLEEP);

        return (session.start().isBefore(end) || session.start().equals(end))
                && (session.end().isAfter(start) || session.end().equals(start));
    }
}