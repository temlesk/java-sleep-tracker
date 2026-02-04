package ru.yandex.practicum.sleeptracker.Model;

import java.time.LocalDateTime;

public record SleepingSession(LocalDateTime start,
                              LocalDateTime end,
                              SleepQuality quality) {
}
