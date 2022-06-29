package com.wlp.mykt.util;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;

import com.wlp.mykt.base.AppProfile;
import com.wlp.mykt.base.KtAppManager;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 异常、崩溃、ANR、Error
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    /**
     * 是否开启日志输出,在Debug状态下开启,
     * 在Release状态下关闭以提示程序性能
     **/
    public static final boolean DEBUG = true;

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;

    /** 程序的Context对象 */
    private Context mContext;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {

    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     */
    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //只要捕获到异常，就重新打开闪屏页
        Intent intent = new Intent(mContext, KtAppManager.Companion.getAppManager().getSplashCls());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);

        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理   
            mDefaultHandler.uncaughtException(thread, ex);
        } else {//如果自己处理了异常，则不会弹出错误对话框，则需要手动退出app
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
            //这里可以跳转到SplashActivity重新登录
        }
    }

    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @return true代表处理该异常，不再向上抛异常，
     * false代表不处理该异常(可以将该log信息存储起来)然后交给上层(这里就到了系统的异常处理)去处理，
     * 简单来说就是true不会弹出那个错误提示框，false就会弹出
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        if (!AppProfile.INSTANCE.getIS_DEBUG()) {//如果不是测试版，不保存错误日志到本地
            return true;
        }
        final StackTraceElement[] stack = ex.getStackTrace();//堆栈踪迹
        final String message = ex.getMessage();
        //使用Toast来显示异常信息   
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    String fileName = "/sdcard/Download/bat_log.txt";
                    File file = new File(fileName);
                    LogUtils.d(file.getAbsolutePath());

                    FileOutputStream fos = new FileOutputStream(file, true);
                    fos.write(message.getBytes());
                    for (int i = 0; i < stack.length; i++) {
                        String threadName = Thread.currentThread().getName();
                        long threadID = Thread.currentThread().getId();
                        StackTraceElement stackTraceElement = stack[i];
                        String className = stackTraceElement.getClassName();
                        String methodName = stackTraceElement.getMethodName();
                        String classFileName = stackTraceElement.getFileName();
                        int lineNumber = stackTraceElement.getLineNumber();
                        StringBuilder content = new StringBuilder();
                        content.append("\n")
                                .append("序号i= ").append(i)
                                .append(",threadID=").append(threadID)
                                .append(",threadName=").append(threadName)
                                .append(",classFileName=").append(classFileName)
                                .append(",className=").append(className)
                                .append(",methodName=").append(methodName)
                                .append(",lineNumber=").append(lineNumber)
                                .append("------------")
                                .append("\n");
                        fos.write(content.toString().getBytes());
                    }
                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Looper.loop();
            }

        }.start();
        return true;
    }

    //使用HTTP Post 发送错误报告到服务器
    //其他信息：app版本、用户信息、手机信息
//    private void postReport(File file) {   
//      在上传的时候还可以将该app的version，该手机的机型等信息一并发送的服务器， 
//      Android的兼容性众所周知，所以可能错误不是每个手机都会报错，还是有针对性的去debug比较好 
//    }   
}