package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.Model.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.Model.SleepQuality;
import ru.yandex.practicum.sleeptracker.Model.SleepingSession;
import ru.yandex.practicum.sleeptracker.function.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final String SESSION_FILE_NAME = "/home/tema/java-sleep-tracker/src/main/resources/sleep_log.txt";
    public static final String SEPARATOR = ";";
    private static final DateTimeFormatter LOG_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private final List<Function<List<SleepingSession>, SleepAnalysisResult>> analyticFunction = List.of(
            new SleepingSessionCounter(),
            new MinDurationSession(),
            new MaxDurationSession(),
            new AverageDurationSession(),
            new QuantitySessionWithBadSleepQuality(),
            new SleeplessNights(),
            new ClassificationUser()
    );

    public static void main(String[] args) {
        SleepTrackerApp app = new SleepTrackerApp();
        try {
            List<SleepingSession> sessions = app.readFile(app.getFile(SESSION_FILE_NAME));

            List<SleepAnalysisResult> results = app.analyzeSession(sessions);
            results.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private List<SleepAnalysisResult> analyzeSession(List<SleepingSession> sessions) {
        return analyticFunction.stream()
                .map(function -> function.apply(sessions))
                .toList();
    }

    private File getFile(String fileName) throws FileNotFoundException {
        Path filePath = Paths.get(fileName);
        File file = filePath.toFile();

        if (!file.exists()) {
            throw new FileNotFoundException(String.format("Не существует файла с именем %s", fileName));
        }
        return file;
    }

    private List<SleepingSession> readFile(File file) {
        List<SleepingSession> sessions = new ArrayList<>();

        try (FileReader fileReader = new FileReader(file, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(fileReader)) {
            sessions = reader.lines()
                    .map(this::parseLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            if (sessions.isEmpty()) {
                System.out.println("Прочитан пустой файл " + file.getName());
            }

        } catch (IOException e) {
            System.out.println("Произошла ошибка при чтении из файла" + file.getName());
        }
        return sessions;
    }

    private Optional<SleepingSession> parseLine(String line) {
        try {
            String[] split = line.split(SEPARATOR);
            LocalDateTime start = LocalDateTime.parse(split[0].trim(), LOG_TIME_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(split[1].trim(), LOG_TIME_FORMATTER);
            SleepQuality quality = SleepQuality.valueOf(split[2].trim().toUpperCase());

            if (start.isAfter(end)) {
                end = end.plusDays(1);
            } else if (end.isBefore(start)) {
                start = start.minusDays(1);
            }

            return Optional.of(new SleepingSession(start, end, quality));

        } catch (IllegalStateException e) {
            return Optional.empty();
        }
    }
}