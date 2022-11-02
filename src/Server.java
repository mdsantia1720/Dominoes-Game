import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * A server class to communicate with each player
 *
 * @author Micky Santiago-Zayas
 * @version July 9, 2021
 */

//https://gist.github.com/chatton/14110d2550126b12c0254501dde73616

public class Server {
    private ArrayList<Long> users = new ArrayList<>();
    private static Socket client;
    private ObjectInputStream reader;
    private ObjectOutputStream pw;

    public Server(Socket client) {
        try {
            reader = new ObjectInputStream(client.getInputStream());
            pw = new ObjectOutputStream(client.getOutputStream());
            User user = (User) reader.readObject();
            users.add(user.getId());
            pw.writeObject(String.format("%s added to game", user));
            pw.flush();
            System.out.printf("%s added to game", user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) throws Exception {
        int port = 4000;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for another client to connect...");
            Socket client = serverSocket.accept();
            new Server(client);
        }
    }
}
