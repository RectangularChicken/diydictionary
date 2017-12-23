package com.rectangularchicken.diydictionaryserver;

import java.net.Socket;

public class Player {

    private final String name;
    private final int number;
    private final Socket client;
    private int score;
    private Entry entry;

    public Player(String name, int number, Socket client) {
        this.name = name;
        this.number = number;
        this.client = client;
    }

    public void sendMessage(String message) {

    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int points) {
        score += points;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Socket getClient() {
        return client;
    }

    public int getScore() {
        return score;
    }

    public Entry getEntry() {
        return entry;
    }
}
