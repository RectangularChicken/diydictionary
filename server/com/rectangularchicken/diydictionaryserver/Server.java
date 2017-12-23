package com.rectangularchicken.diydictionaryserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final String VERSION = "0.0.1";

    public static void main(String[] args) {
        System.out.println("DIY Dictionary Server v" + VERSION);
        System.out.println("By Santiago Benoit");
        int port = Integer.parseInt(args[0]);
        ExecutorService executor = null;
        Game game = new Game();
        //game.start();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            executor = Executors.newFixedThreadPool(Game.MAX_PLAYERS);
            System.out.println("Server started");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Server stopped");
            }));
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Runnable worker = new RequestHandler(game, clientSocket);
                executor.execute(worker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }
}
