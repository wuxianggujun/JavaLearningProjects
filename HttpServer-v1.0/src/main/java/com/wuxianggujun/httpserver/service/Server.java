package com.wuxianggujun.httpserver.service;

import com.wuxianggujun.httpserver.data.Data;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ����¾�
 * @description �������
 * @date 2022/06/13
 */
public class Server implements Runnable {
    //�����˿�
    private int port = 8088;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        //�߳�������
        //����ServerSocket����
        ServerSocket serverSocket = null;
        try {

            //����ServerSocket����
            serverSocket = new ServerSocket(port);
            //��ʼ����
            System.out.println("��ʼ����....");
            while (Data.isRun){
                Socket socket = serverSocket.accept();
                System.out.println("���յ�����......");
                //��socket����RequestExecute����
                RequestExecute requestExecute =new  RequestExecute(socket);
                requestExecute.start();
            }

            //�ر�ServerSocket
            serverSocket.close();
            serverSocket= null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("�˿�" + port + "�����쳣" + e.getMessage());
        }


    }

    public static void main(String[] args) {
        Server server = new Server(8088);
        new Thread(server).start();
    }

}
