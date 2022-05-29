import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static Socket socket;

    public static boolean connectionState = false;

    public static void main(String[] args) {
        while (!connectionState) {
            try {
                connect();
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void connect() {
        try {
            //创建流套接字并将其连接到指定IP地址处的指定端口号
            socket = new Socket("localhost", 8080);
            connectionState = true;
            if (connectionState) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    new Thread(new ClientListen(socket)).start();
                    new Thread(new ClientSend(socket, oos)).start();
                    new Thread(new ClientHeart(socket, oos)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            connectionState = false;
        }
    }


    public static void reconnect() {
        //判断有没有断开连接开始重连
        while (!connectionState) {
            System.out.println("正在尝试重新连接...");
            connect();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class ClientListen implements Runnable {

    private Socket socket;

    public ClientListen(Socket socket) {
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

class ClientSend implements Runnable {

    private Socket socket;
    private ObjectOutputStream oos;


    public ClientSend(Socket socket, ObjectOutputStream oos) {
        this.socket = socket;
        this.oos = oos;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入要发送的内容:");
                String string = scanner.nextLine();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "chat");
                jsonObject.put("msg", string);
                oos.writeObject(jsonObject);
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
                Client.connectionState = false;
                Client.reconnect();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }

}

class ClientHeart implements Runnable {
    private Socket socket;

    private ObjectOutputStream oos;

    public ClientHeart(Socket socket, ObjectOutputStream oos) {
        this.socket = socket;
        this.oos = oos;
    }


    @Override
    public void run() {
        try {
            System.out.println("开始心跳包.....");
            while (true) {
                //五秒发送一次心跳
                Thread.sleep(5000);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "heart");
                jsonObject.put("msg", "心跳包");
                oos.writeObject(jsonObject);
                oos.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            try {
                socket.close();
                Client.connectionState = false;
                Client.reconnect();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }


}