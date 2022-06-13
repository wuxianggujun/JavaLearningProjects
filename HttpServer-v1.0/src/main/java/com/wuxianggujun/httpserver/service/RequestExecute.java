package com.wuxianggujun.httpserver.service;

import java.io.*;
import java.net.Socket;

/**
 * @author ����¾�
 * @description �������߳���
 * @date 2022/06/13
 */
public class RequestExecute extends Thread {
    //��Socket����Ϊ��Ա����
    private Socket socket;

    public RequestExecute(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //��Socket��ȡ����������Ȼ�����������ȡ������
        InputStream in = null;//���ֽ�������ת��Ϊ�����ַ�������
        InputStreamReader reader = null;//ת����
        BufferedReader bufferedReader = null;//�ַ�������
        //������������������ָ��ͻ��˵�
        OutputStream out = null;
        PrintWriter printWriter = null;

        try {
            //��Socket�л�ȡ�ֽ������
            out = socket.getOutputStream();
            //���ֽ��������װ���ַ���
            printWriter = new PrintWriter(out);

            //��socket��ȡ�ֽ�������
            in = socket.getInputStream();
            //ת���Ͱ�װ
            reader = new InputStreamReader(in);
            bufferedReader = new BufferedReader(reader);
            //ѭ���Ĵ��ַ����л�ȡ�ַ�
            String line = null;
            int lineNum = 1;
            //�洢����·��
            String reqPath = "";
            String host = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                //��������������
                if (lineNum == 1) {//��һ��
                    //ʹ�ÿո�ָ��ַ���
                    String[] infos = line.split(" ");
                    if (infos != null && infos.length > 2) {
                        reqPath = infos[1];//����·��
                    } else {
                        throw new RuntimeException("�����н���ʧ�ܣ�" + line);
                    }
                } else {
                    //���������У�ȡ��host����
                    String[] split = line.split(": ");
                    if (split != null && split.length == 2) {
                        //ȡ��host
                        if (split[0].equals("Host"))
                            host = split[1];
                    }
                }
                lineNum++;
                if (line.equals("")) {//��ȡ�����оͽ�������Ϊhttp�����ǳ�����,�޷���ȡ���ļ�ĩβ.
                    break;
                }
            }
            //�����������
            if (!reqPath.equals("")) {
                System.out.println("��������:Http://" + host + reqPath);
                //����������Ӧ�ͻ��� //ֱ����Ӧһ��indexҳ��
                if (reqPath.equals("/")) {
                    //û����Դ����
                    printWriter.println("HTTP/1.1 200 OK");//�����Ӧ��
                    printWriter.println("Content-Type: text/html;charset=utf-8");
                    //�������
                    printWriter.println();//��ʾ��Ӧͷ��������ʼ��Ӧ����
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
