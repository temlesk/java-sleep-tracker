package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Model.Chronotype;
import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;


public class ClassificationUser implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final LocalTime OWL_START = LocalTime.of(23, 0);
    private static final LocalTime OWL_END = LocalTime.of(9, 0);
    private static final LocalTime LARK_START = LocalTime.of(22, 0);
    private static final LocalTime LARK_END = LocalTime.of(7, 0);
    public static final String TITLE = "Классификация пользователя";

    private int owlCount;
    private int larkCount;
    private int pigeonCount;


    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        init();
        sleepingSessions.forEach(this::classification);
        Chronotype chronotype = typeUser();
        return new SleepAnalysisResult(TITLE, chronotype);
    }

    private void init() {
        owlCount = 0;
        larkCount = 0;
        pigeonCount = 0;
    }

    private void classification(SleepingSession session) {
        LocalTime startTime = session.start().toLocalTime();
        LocalTime endTime = session.end().toLocalTime();

        if (!(startTime.isAfter(endTime) && startTime.isBefore(OWL_START))
                && (endTime.isAfter(OWL_END) || endTime.equals(OWL_END))) {
            owlCount++;
        } else if ((startTime.isBefore(LARK_START) || startTime.equals(LARK_START))
                && (endTime.isBefore(LARK_END) || endTime.equals(LARK_END))) {
            larkCount++;
        } else {
            pigeonCount++;
        }
    }

    private Chronotype typeUser() {
        if (owlCount > larkCount && owlCount > pigeonCount) {
            return Chronotype.OWL;
        } else if (larkCount > owlCount && larkCount > pigeonCount) {
            return Chronotype.LARK;
        } else {
            return Chronotype.PIGEON;
        }
    }
}