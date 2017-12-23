package com.rectangularchicken.diydictionaryserver;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int MAX_PLAYERS = 8;
    private volatile List<Player> players;
    private volatile String currentTerm;
    private volatile int playerTurn;

    public Game() {
        players = new ArrayList<>();
        currentTerm = "";
        playerTurn = 0;
    }

//    public void start() {
//        new Thread(() -> {
//            while (true) {
//                if (players.size() >= 3) {
//
//                }
//            }
//        }).start();
//    }

    public synchronized String processMessage(String message, Socket client) {
        String[] parts = message.split("\n");
        try {
            switch (parts[0]) {
                case "newplayer":
                    if (players.size() < MAX_PLAYERS) {
                        players.add(new Player(parts[1], players.size() + 1, client));
                        return "success";
                    } else {
                        return "gamefull";
                    }
                case "everybodyin":
                    if (players.size() >= 3) {
                        playerTurn = 1;
                        for (Player player : players) {
                            player.sendMessage("starting");
                        }
                        return "success";
                    } else {
                        return "error";
                    }
                case "quit":
                    players.remove(Integer.parseInt(parts[1]));
                    return "success";
                case "submitterm":
                    if (Integer.parseInt(parts[1]) == playerTurn) {
                        currentTerm = parts[2];
                        for (Player player : players) {
                            player.sendMessage("newterm\n" + currentTerm);
                        }
                        return "success";
                    } else {
                        return "error";
                    }
                case "submitdefinition":
                    players.get(Integer.parseInt(parts[1])).setEntry(new Entry(currentTerm, parts[2]));
                    return "success";
                case "submitvote":

                    return "success";
                default:
                    return "error";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public String getCurrentTerm() {
        return currentTerm;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

}
