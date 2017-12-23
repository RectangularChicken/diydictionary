package com.rectangularchicken.diydictionaryserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private final Game game;
    private final Socket client;

    public RequestHandler(Game game, Socket client) {
        this.game = game;
        this.client = client;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            String messageIn = in.readLine();
            String messageOut = game.processMessage(messageIn, client);
            out.println(messageOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
