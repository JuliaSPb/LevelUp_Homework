package ru.levelup.julia_kalujnaya.qa.homework_3.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-сущность, представляющий объект предметной области "Сеанс", со следующими полями:
 * movie - ссылка на фильм
 * hall - ссылка на зал
 * startTime - время начала сеанса
 * price - цена билета
 * availableTickets - список доступных билетов
 * boughtTickets - список купленных билетов
 *
 * @author Юлия Калюжная
 */
public class Session {
    private Movie movie;
    private Hall hall;
    private String startTime;
    private double price;
    private List<Ticket> availableTickets;
    private List<Ticket> boughtTickets;

    public Session(Movie movie, Hall hall, String startTime, double price) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.price = price;
        this.availableTickets = new ArrayList<>();
        for (int i = 0; i < hall.getAmountOfSeats(); i++) {
            this.availableTickets.add(new Ticket(i + 1, this));
        }
        this.boughtTickets = new ArrayList<>();
    }

    public Hall getHall() {
        return hall;
    }

    public List<Ticket> getAvailableTickets() {
        return availableTickets;
    }

    @Override
    public String toString() {
        return hall.getName() + ", время начала " + startTime + ", цена " + price + " руб., количество свободных мест " +
                availableTickets.size();
    }

    /**
     * Возвращает список свободных мест для данного сеанса в виде строки
     * @return Возвращает список свободных мест через запятую
     */
    public String getAvailableSeats() {
        StringBuilder seats = new StringBuilder();
        for (int i = 0; i < availableTickets.size(); i++) {
            if (i != 0) {
                seats.append(", ");
            }
            seats.append(availableTickets.get(i).getSeat());
        }
        return seats.toString();
    }

    /**
     * Покупка билета с заданным местом (если оно свободно)
     * @param seat - номер места
     * @return Возвращает true в случае успешной покупки
     */
    public boolean buyTicket(int seat) {
        for (Ticket ticket: availableTickets) {
            if (ticket.getSeat() == seat) {
                availableTickets.remove(ticket);
                boughtTickets.add(ticket);
                return true;
            }
        }
        return false;
    }
}