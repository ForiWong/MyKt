package com.wlp.mykt.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import com.wlp.mykt.base.AppProfile;

import java.util.Locale;

/**
 * description: app信息工具类
 */
public class AppUtil {
    /**
     * 获取版本名称
     * @return 版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取版本号
     * @return 版本号
     */
    public static long getVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) return packageInfo.getLongVersionCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取App的名称
     * @return 名称
     */
    public static String getAppName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            //获取应用 信息
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            //获取albelRes
            int labelRes = applicationInfo.labelRes;
            //返回App的名称
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前手机系统语言。
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前手机系统版本号
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取当前手机系统版本号Number
     * @return  系统版本号Number
     */
    public static int getSystemVersionNumber() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机型号
     * @return  手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 判断当前应用是否是debug状态
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断应用是否安装 为上架方便，不要这个方法
     * @param packageName
     * @return
     */
    public static boolean isAppAvailable(String packageName) {
//        final PackageManager packageManager = Utils.getContext().getPackageManager();
//        // 获取所有已安装程序的包信息
//        List<PackageInfo> appList = packageManager.getInstalledPackages(0);
//        for (int i = 0; i < appList.size(); i++) {
//            if (appList.get(i).packageName.equalsIgnoreCase(packageName))
//                return true;
//        }
        return false;
    }

    /**
     * 获取渠道号
     */
    public static String getChannelValue(Context mContext) {
        if(AppProfile.INSTANCE.getIS_DEBUG()) return "test";//假如是debug，渠道为test
        return getAppInfo(mContext, "UMENG_CHANNEL", "bat");//默认渠道是bat
    }

    /**
     * 判断当前渠道是否为tencent/应用宝
     */
    public static boolean isChannelTencent(Context mContext){
        return TextUtils.equals("tencent", getChannelValue(mContext));
    }

    public static boolean isChannelOppo(Context mContext){
        return TextUtils.equals("oppo", getChannelValue(mContext));
    }

    public static String getAppInfo(Context mContext, String key, String defaultValue) {
        ApplicationInfo appInfo;
        String result = defaultValue;
        try {
            appInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            Object object = appInfo.metaData.get(key);
            if(object != null) result = object.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isCantAttache(Context context) {
        if(context == null) return true;
        if (context instanceof Activity) {
            return isCantAttache((Activity) context);
        }
        return false;
    }

    public static boolean isCantAttache(Activity activity){
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }
}
