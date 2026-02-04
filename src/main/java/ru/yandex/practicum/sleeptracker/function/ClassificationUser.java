package ru.yandex.practicum.sleeptracker.function;

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
        String kind = typeUser();
        return new SleepAnalysisResult(TITLE, kind);
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

//        if (session.start().toLocalDate().isBefore(session.end().toLocalDate())) {
//            if (startTime.isBefore(OWL_START) &&
//                    endTime.isAfter(OWL_END)) {
//                owlCount++;
//            } else if (startTime.isBefore(LARK_START) &&
//                    endTime.isBefore(LARK_END)) {
//                larkCount++;
//            } else {
//                pigeonCount++;
//            }
//        } else {
//            if (startTime.isAfter(OWL_START) &&
//                    endTime.isAfter(OWL_END)) {
//                owlCount++;
//            } else if (startTime.isBefore(LARK_START) &&
//                    endTime.isBefore(LARK_END)) {
//                larkCount++;
//            } else {
//                pigeonCount++;
//            }
//        }}

//
    private String typeUser() {
        if (owlCount > larkCount && owlCount > pigeonCount) {
            return "Сова";
        } else if (larkCount > owlCount && larkCount > pigeonCount) {
            return "Жаворонок";
        } else {
            return "Голубь";
        }
    }
}


//public class ClassificationUser implements Function<List<SleepingSession>, SleepAnalysisResult> {
//    public static final String TITLE = "хронотип";
//    private static final LocalTime OWL_LEFT = LocalTime.of(23, 0);
//    private static final LocalTime OWL_RIGHT = LocalTime.of(9, 0);
//    private static final LocalTime LARK_LEFT = LocalTime.of(22, 0);
//    private static final LocalTime LARK_RIGHT = LocalTime.of(7, 0);
//
//    private int owlCount;
//    private int larkCount;
//    private int hummingbirdCount;
//
//    @Override
//    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
//        initialize();
//        sessions.forEach(this::analyseSession);
//        String chronotype = analyseCounters();
//        return new SleepAnalysisResult(TITLE, chronotype);
//    }
//
//    private void initialize() {
//        owlCount = 0;
//        larkCount = 0;
//        hummingbirdCount = 0;
//    }
//
//    private void analyseSession(SleepingSession session) {
//        if (isOwlSession(session)) {
//            owlCount++;
//        } else if (isLarkSession(session)) {
//            larkCount++;
//        } else {
//            hummingbirdCount++;
//        }
//    }
//
//    private boolean isOwlSession(SleepingSession session) {
//        LocalTime start = session.start().toLocalTime();
//        LocalTime end = session.end().toLocalTime();
//        return !(start.isAfter(end) && start.isBefore(OWL_LEFT))
//                && (end.isAfter(OWL_RIGHT) || end.equals(OWL_RIGHT));
//    }
//
//    private boolean isLarkSession(SleepingSession session) {
//        LocalTime start = session.start().toLocalTime();
//        LocalTime end = session.end().toLocalTime();
//        return (start.isBefore(LARK_LEFT) || start.equals(LARK_LEFT))
//                && (end.isBefore(LARK_RIGHT) || end.equals(LARK_RIGHT));
//    }
//
//    private String analyseCounters() {
//        if (owlCount > larkCount && owlCount > hummingbirdCount) {
//            return "Сова";
//        } else if (larkCount > owlCount && larkCount > hummingbirdCount) {
//            return "Жаворонок";
//        } else {
//            return "Голубь";
//        }
//    }
//}