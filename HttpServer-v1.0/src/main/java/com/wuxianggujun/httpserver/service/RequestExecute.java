package com.wuxianggujun.httpserver.service;

import com.wuxianggujun.httpserver.data.Data;

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

        //先判断服务器是否处于暂停状态
        if (Data.isPush) {
            try {
                printWriter = new PrintWriter(socket.getOutputStream());
                //没有资源名称
                printWriter.println("HTTP/1.1 200 OK");//输出响应行
                printWriter.println("Content-Type: text/html;charset=utf-8");
                //输出空行
                printWriter.println();//表示响应头结束，开始响应内容
                printWriter.println("<h2>Http Server is Pause</h2>");
                printWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                printWriter.close();
            }
            return;
        }
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

                } else {

                    //查找资源
                    //取出后缀
                    String ext = reqPath.substring(reqPath.lastIndexOf(".") + 1);
                    reqPath = reqPath.substring(1);//除去前面的/
                    //判断是在根目录下还是在其他目录下
                    if (reqPath.contains("/")) {
                        //子目录下

                        File file = new File(Data.resourcePath + reqPath);
                        if (file.exists() && file.isFile()) {
                            //文件存在
                            response200(out, file.getName(), ext);
                        } else {
                            response404(out);
                        }
                        //输出响应行

                    } else {
                        //根目录下
                        //判断资源是否存在
                        //获取根目录下的所有文件的名称
                        File root = new File(Data.resourcePath);
                        if (root.isDirectory()) {
                            File[] list = root.listFiles();
                            boolean isExist = false;//标记访问的资源是否存在

                            for (File file : list) {
                                if (file.isFile() && file.getName().equals(reqPath + "." + ext)) {
                                    //文件存在
                                    isExist = true;
                                    break;
                                }
                            }
                            if (isExist) {
                                //文件存在
                                response200(out, Data.resourcePath + reqPath, ext);
                            } else {
                                //文件不存在
                                response404(out);
                            }


                        } else {
                            //根目录不存在
                            printWriter.println("HTTP/1.1 404 Not Found");//输出响应行
                        }


                    }

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

    /**
     * 将指定的文件输出到输出流中
     */
    private void response200(OutputStream out, String filePath, String ext) {
        PrintWriter printWriter = null;
        //准备输入流读取磁盘上的文件
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;

        try {

            if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
                out.write("HTTP/1.1 200 OK\r\n".getBytes());//输出响应行
                out.write(new StringBuilder().append("Content-Type: image/").append(ext).append("\r\n").toString().getBytes());
                out.write("\r\n".getBytes());//表示响应头结束，开始响应内容
                //创建输入流
                in = new FileInputStream(filePath);
                int len = -1;
                byte[] bytes = new byte[1024];
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                    out.flush();
                }
            } else if (ext.equals("html") || ext.equals("htm") || ext.equals("js") || ext.equals("css") || ext.equals("json")) {
                printWriter = new PrintWriter(out);

                printWriter.println("HTTP/1.1 200 OK");//输出响应行
                printWriter.println("Content-Type: text/html;charset=utf-8");
                if (ext.equals("js"))
                    printWriter.println("Content-Type: application/javascript;charset=utf-8");
                else if (ext.equals("css"))
                    printWriter.println("Content-Type: text/css;charset=utf-8");
                else if (ext.equals("htm") || ext.equals("html"))
                    printWriter.println("Content-Type: text/html;charset=utf-8");
                else if (ext.equals("json"))
                    printWriter.println("Content-Type: application/json;charset=utf-8");

                printWriter.println();//表示响应头结束，开始响应内容

                in = new FileInputStream(filePath);
                reader = new InputStreamReader(in);
                bufferedReader = new BufferedReader(reader);
                //写出数据
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    printWriter.println(line);
                    printWriter.flush();
                }


            } else {
                response404(out);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (printWriter != null) printWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 响应404
     */
    private void response404(OutputStream out) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(out);

            printWriter.println("HTTP/1.1 404");//输出响应行
            printWriter.println("Content-Type: text/html;charset=utf-8");
            //输出空行
            printWriter.println();//表示响应头结束，开始响应内容
            printWriter.println("<h2>Resource Not Found!</h2>");
            printWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (printWriter != null) printWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
