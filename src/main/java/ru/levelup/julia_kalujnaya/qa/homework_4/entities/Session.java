package ru.levelup.julia_kalujnaya.qa.homework_4.entities;

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

    public Movie getMovie() {
        return movie;
    }

    public Hall getHall() {
        return hall;
    }

    public String getStartTime() {
        return startTime;
    }

    public double getPrice() {
        return price;
    }

    public List<Ticket> getAvailableTickets() {
        return availableTickets;
    }

    public List<Ticket> getBoughtTickets() {
        return boughtTickets;
    }

    /**
     * Покупка билета с заданным местом
     * @param seat - номер места
     * @return Возвращает 0 в случае успешной покупки, -1 при ошибочных входных данных (неверный номер места)
     * и -2, если билетов на сеанс нет или данное место занято
     */
    public int buyTicket(int seat) {
        if ((seat <= 0) || (seat > getHall().getAmountOfSeats()))
            return -1;
        if (getAvailableTickets().size() > 0) {
            for (Ticket ticket : availableTickets) {
                if (ticket.getSeat() == seat) {
                    availableTickets.remove(ticket);
                    boughtTickets.add(ticket);
                    return 0;
                }
            }
        }
        return -2;
    }
}