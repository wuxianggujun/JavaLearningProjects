package com.wuxianggujun.httpserver.service;

import java.io.*;
import java.net.Socket;

/**
 * @author 无相孤君
 * @description 请求处理线程类
 * @date 2022/06/13
 */
public class RequestExecute extends Thread {
    //将Socket定义为成员变量
    private Socket socket;

    public RequestExecute(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //从Socket中取出输入流，然后从输入流中取出数据
        InputStream in = null;//将字节输入流转换为缓冲字符输入流
        InputStreamReader reader = null;//转换流
        BufferedReader bufferedReader = null;//字符缓冲流
        //申明输出流，输出流是指向客户端的
        OutputStream out = null;
        PrintWriter printWriter = null;

        try {
            //从Socket中获取字节输出流
            out = socket.getOutputStream();
            //将字节输出流包装成字符流
            printWriter = new PrintWriter(out);

            //从socket获取字节输入流
            in = socket.getInputStream();
            //转换和包装
            reader = new InputStreamReader(in);
            bufferedReader = new BufferedReader(reader);
            //循环的从字符流中获取字符
            String line = null;
            int lineNum = 1;
            //存储请求路径
            String reqPath = "";
            String host = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                //解析请求行数据
                if (lineNum == 1) {//第一行
                    //使用空格分隔字符串
                    String[] infos = line.split(" ");
                    if (infos != null && infos.length > 2) {
                        reqPath = infos[1];//请求路径
                    } else {
                        throw new RuntimeException("请求行解析失败！" + line);
                    }
                } else {
                    //解析其他行，取出host内容
                    String[] split = line.split(": ");
                    if (split != null && split.length == 2) {
                        //取出host
                        if (split[0].equals("Host"))
                            host = split[1];
                    }
                }
                lineNum++;
                if (line.equals("")) {//读取到空行就结束，因为http请求是长连接,无法读取到文件末尾.
                    break;
                }
            }
            //输出请求数据
            if (!reqPath.equals("")) {
                System.out.println("处理请求:Http://" + host + reqPath);
                //根据请求响应客户端 //直接响应一个index页面
                if (reqPath.equals("/")) {
                    //没有资源名称
                    printWriter.println("HTTP/1.1 200 OK");//输出响应行
                    printWriter.println("Content-Type: text/html;charset=utf-8");
                    //输出空行
                    printWriter.println();//表示响应头结束，开始响应内容
                    printWriter.println("<h2>Index Http Server</h2>");
                    printWriter.flush();

                }else{

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) in.close();
                if (reader != null) reader.close();
                if (bufferedReader != null) bufferedReader.close();
                if (printWriter != null) printWriter.close();
                if (out != null) out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
