package com.wuxianggujun.httpserver.service;

import com.wuxianggujun.httpserver.data.Data;

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

        //���жϷ������Ƿ�����ͣ״̬
        if (Data.isPush) {
            try {
                printWriter = new PrintWriter(socket.getOutputStream());
                //û����Դ����
                printWriter.println("HTTP/1.1 200 OK");//�����Ӧ��
                printWriter.println("Content-Type: text/html;charset=utf-8");
                //�������
                printWriter.println();//��ʾ��Ӧͷ��������ʼ��Ӧ����
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

                } else {

                    //������Դ
                    //ȡ����׺
                    String ext = reqPath.substring(reqPath.lastIndexOf(".") + 1);
                    reqPath = reqPath.substring(1);//��ȥǰ���/
                    //�ж����ڸ�Ŀ¼�»���������Ŀ¼��
                    if (reqPath.contains("/")) {
                        //��Ŀ¼��

                        File file = new File(Data.resourcePath + reqPath);
                        if (file.exists() && file.isFile()) {
                            //�ļ�����
                            response200(out, file.getName(), ext);
                        } else {
                            response404(out);
                        }
                        //�����Ӧ��

                    } else {
                        //��Ŀ¼��
                        //�ж���Դ�Ƿ����
                        //��ȡ��Ŀ¼�µ������ļ�������
                        File root = new File(Data.resourcePath);
                        if (root.isDirectory()) {
                            File[] list = root.listFiles();
                            boolean isExist = false;//��Ƿ��ʵ���Դ�Ƿ����

                            for (File file : list) {
                                if (file.isFile() && file.getName().equals(reqPath + "." + ext)) {
                                    //�ļ�����
                                    isExist = true;
                                    break;
                                }
                            }
                            if (isExist) {
                                //�ļ�����
                                response200(out, Data.resourcePath + reqPath, ext);
                            } else {
                                //�ļ�������
                                response404(out);
                            }


                        } else {
                            //��Ŀ¼������
                            printWriter.println("HTTP/1.1 404 Not Found");//�����Ӧ��
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
     * ��ָ�����ļ�������������
     */
    private void response200(OutputStream out, String filePath, String ext) {
        PrintWriter printWriter = null;
        //׼����������ȡ�����ϵ��ļ�
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;

        try {

            if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
                out.write("HTTP/1.1 200 OK\r\n".getBytes());//�����Ӧ��
                out.write(new StringBuilder().append("Content-Type: image/").append(ext).append("\r\n").toString().getBytes());
                out.write("\r\n".getBytes());//��ʾ��Ӧͷ��������ʼ��Ӧ����
                //����������
                in = new FileInputStream(filePath);
                int len = -1;
                byte[] bytes = new byte[1024];
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                    out.flush();
                }
            } else if (ext.equals("html") || ext.equals("htm") || ext.equals("js") || ext.equals("css") || ext.equals("json")) {
                printWriter = new PrintWriter(out);

                printWriter.println("HTTP/1.1 200 OK");//�����Ӧ��
                printWriter.println("Content-Type: text/html;charset=utf-8");
                if (ext.equals("js"))
                    printWriter.println("Content-Type: application/javascript;charset=utf-8");
                else if (ext.equals("css"))
                    printWriter.println("Content-Type: text/css;charset=utf-8");
                else if (ext.equals("htm") || ext.equals("html"))
                    printWriter.println("Content-Type: text/html;charset=utf-8");
                else if (ext.equals("json"))
                    printWriter.println("Content-Type: application/json;charset=utf-8");

                printWriter.println();//��ʾ��Ӧͷ��������ʼ��Ӧ����

                in = new FileInputStream(filePath);
                reader = new InputStreamReader(in);
                bufferedReader = new BufferedReader(reader);
                //д������
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
     * ��Ӧ404
     */
    private void response404(OutputStream out) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(out);

            printWriter.println("HTTP/1.1 404");//�����Ӧ��
            printWriter.println("Content-Type: text/html;charset=utf-8");
            //�������
            printWriter.println();//��ʾ��Ӧͷ��������ʼ��Ӧ����
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
