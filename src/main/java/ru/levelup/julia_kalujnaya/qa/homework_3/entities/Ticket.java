package ru.levelup.julia_kalujnaya.qa.homework_3.entities;

/**
 * Класс-сущность, представляющий объект предметной области "Билет", со следующими полями:
 * seat - номер места в зале
 * session - ссылка на сеанс
 *
 * @author Юлия Калюжная
 */
public class Ticket {
    private int seat;
    private Session session;

    public Ticket(int seat, Session session) {
        this.seat = seat;
        this.session = session;
    }

    public int getSeat() {
        return seat;
    }
}