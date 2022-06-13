package com.wuxianggujun.httpserver;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFrame extends JFrame {

    private JLabel labPort;
    private JLabel labInfo;
    private JLabel labPath;
    private JTextField textPort;
    private JTextField textPath;
    private JButton btnStartServer;
    private JButton btnPushServer;
    private JButton btnStopServer;
    private JButton btnSetPath;
    private JPanel contentPanel;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    public MainFrame(){
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(200,200,800,500);
        this.setTitle("HttpServer����");
        this.setResizable(false);
        //���������
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        this.setContentPane(contentPanel);
        //�˿�����
        labPort = new JLabel("�����Ķ˿ں�");
        labPort.setBounds(15,10,100,25);
        contentPanel.add(labPort);

        textPort = new JTextField("8088");
        textPort.setBounds(120,10,150,25);
        contentPanel.add(textPort);

        //������ť
        btnStartServer = new JButton("��������");
        btnStartServer.setBounds(300,10,120,25);
        contentPanel.add(btnStartServer);

        btnPushServer = new JButton("��ͣ����");
        btnPushServer.setBounds(440,10,120,25);
        btnPushServer.setEnabled(false);
        contentPanel.add(btnPushServer);

        btnStopServer = new JButton("ֹͣ����");
        btnStopServer.setBounds(580,10,120,25);
        btnStopServer.setEnabled(false);
        contentPanel.add(btnStopServer);

        //��Դ·������
        labPath =  new JLabel("��Դ·��λ��");
        labPath.setBounds(15,45,100,25);
        contentPanel.add(labPath);

        textPath = new JTextField("");
        textPath.setBounds(130,45,500,25);
        contentPanel.add(textPath);

        btnSetPath = new JButton("������Դλ��");
        btnSetPath.setBounds(640,45,120,25);
        contentPanel.add(btnSetPath);

        //����̨
        textArea = new JTextArea("--����̨--\r\n");
        textArea.setLineWrap(true);
        scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(15,80,770,350);
        contentPanel.add(scrollPane);

        //������Դ�ļ��а�ť�¼�
        btnSetPath.addActionListener(e -> {

        });
        //������ť�¼�
        btnStartServer.addActionListener(e -> {

        });
        //��ͣ��ť�¼�
        btnPushServer.addActionListener(e -> {

        });

        //ֹͣ��ť�¼�
        btnStopServer.addActionListener(e->{

        });
        this.setVisible(true);
    }

    //�����־������̨
    public void printLog(final String msg) {
        new Thread(() -> {
            String date = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date());
            String info =textArea.getText()+ date + " "+msg+"\r\n";
            textArea.setText(info);
        }).start();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}
