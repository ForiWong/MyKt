package com.wlp.mykt.util;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by wlp on 2021/1/20
 * Description:控件工具类
 */
public class ViewUtils {

    public static void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void setDialogWidthMathParent(Context context, Dialog dialog){
        setDialogWidthMathParent(context, dialog, 72);
    }

    public static void setDialogWidthMathParent(Context context, Dialog dialog, int margin){
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        //TODO 改为kotlin
//        lp.width = ScreenUtil.getScreenWidth(context) - ScreenUtil.getPxByDp(context, margin);
        dialog.getWindow().setAttributes(lp);
    }

    public static boolean isTextNull(Context context, TextView textView){
        if(context == null || textView == null) return false;
        return TextUtils.isEmpty(textView.getText().toString().trim());
    }

    public static String getText(Context context, TextView textView){
        if(context == null || textView == null) return null;
        return textView.getText().toString().trim();
    }

    /**
     * 复制到粘贴板
     */
    public static void copyToClipboard(Context context,String str){
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("BAT", str);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }
}
