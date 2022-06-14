package com.wuxianggujun.httpserver.service;

import com.wuxianggujun.httpserver.MainFrame;
import com.wuxianggujun.httpserver.data.Data;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 无相孤君
 * @description 服务监听
 * @date 2022/06/13
 */
public class Server implements Runnable {
    //监听端口
    private int port = 8088;
    private MainFrame mainFrame;

    public Server(int port,MainFrame mainFrame) {
        this.port = port;
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        //线程主程序
        //申明ServerSocket对象
        ServerSocket serverSocket = null;
        try {

            //创建ServerSocket对象
            serverSocket = new ServerSocket(port);

            //输出日志
            mainFrame.printLog("开始监听...."+port);
            mainFrame.printLog("静态资源路径："+Data.resourcePath);
            mainFrame.printLog("服务器启动成功！");
            while (Data.isRun){
                Socket socket = serverSocket.accept();
                System.out.println("接收到请求......");
                //将socket交给RequestExecute处理
                RequestExecute requestExecute =new  RequestExecute(socket);
                requestExecute.start();
            }

            //关闭ServerSocket
            serverSocket.close();
            serverSocket= null;
            mainFrame.printLog("服务器关闭！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("端口" + port + "监听异常" + e.getMessage());
        }


    }

}
