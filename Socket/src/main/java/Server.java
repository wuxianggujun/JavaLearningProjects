import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

        try {
            System.out.println("Server is running...");
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerListen(socket)).start();
                new Thread(new ServerSend(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class ServerListen implements Runnable {

    private Socket socket;

    public ServerListen(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

class ServerSend implements Runnable {
    private Socket socket;

    public ServerSend(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入要发送的内容：");
                String string = scanner.nextLine();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "chat");
                jsonObject.put("msg", string);
                oos.writeObject(jsonObject);
                //刷新缓冲区
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
