package com.wuxianggujun.test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class Test {
    public static void main(String[] args) {
        // ��ȡ Java �̹߳��� MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // ����Ҫ��ȡͬ���� monitor �� synchronizer ��Ϣ������ȡ�̺߳��̶߳�ջ��Ϣ
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // �����߳���Ϣ������ӡ�߳� ID ���߳�������Ϣ
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
