package com.wuxianggujun.httpserver.service;

import com.wuxianggujun.httpserver.MainFrame;
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
    private MainFrame mainFrame;

    public Server(int port,MainFrame mainFrame) {
        this.port = port;
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        //�߳�������
        //����ServerSocket����
        ServerSocket serverSocket = null;
        try {

            //����ServerSocket����
            serverSocket = new ServerSocket(port);

            //�����־
            mainFrame.printLog("��ʼ����...."+port);
            mainFrame.printLog("��̬��Դ·����"+Data.resourcePath);
            mainFrame.printLog("�����������ɹ���");
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
            mainFrame.printLog("�������رգ�");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("�˿�" + port + "�����쳣" + e.getMessage());
        }


    }

}
