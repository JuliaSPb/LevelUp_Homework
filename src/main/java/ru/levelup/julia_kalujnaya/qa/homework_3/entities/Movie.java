package ru.levelup.julia_kalujnaya.qa.homework_3.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-сущность, представляющий объект предметной области "Фильм", со следующими полями:
 * title - название фильма
 * duration - продолжительность в минутах
 * description - описание фильма
 * actors - перечень актёров
 * sessions - список сеансов для данного фильма
 *
 * @author Юлия Калюжная
 */
public class Movie {
    private String title;
    private int duration;
    private String description;
    private String actors;
    private List<Session> sessions;

    public Movie(String title, int duration, String description, String actors) {
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.actors = actors;
        this.sessions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    /**
     * Добавляет сеанс к списку сеансов
     * @param session - добавляемый сеанс
     */
    public void addSession(Session session) {
        if (session != null) {
            sessions.add(session);
        }
    }

    @Override
    public String toString() {
        StringBuilder sessionsToString = new StringBuilder();
        for (int i = 1; i <= sessions.size(); i++) {
            sessionsToString.append("Сеанс ").append(i).append(": ").append(sessions.get(i - 1)).append("\n");
        }
        return "Фильм: \"" + title + "\"\n" +
                "Продолжительность: " + duration + " мин.\n" +
                "Описание: " + description + "\n" +
                "В ролях: " + actors + "\n" + sessionsToString;
    }
}