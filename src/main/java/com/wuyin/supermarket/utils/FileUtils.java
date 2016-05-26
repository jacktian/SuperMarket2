package com.wuyin.supermarket.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by yinlong on 2016/5/5.
 */
public class FileUtils {

    public static final String CACHE = "cache";

    public static final String ROOT = "GooglePlay";

    public static File getDir(String str) {

        StringBuilder path = new StringBuilder();
        if (isSdAvailable()) {
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator); //添加斜線
            path.append(ROOT);
            path.append(File.separator);
            path.append(str);

        } else {
            File cacheDir = UIUtils.getContext().getCacheDir();
            path.append(cacheDir.getAbsolutePath());
            path.append(File.separator);
            path.append(str);
        }

        File file = new File(path.toString());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs(); // 創建文件
        }
        return file;
    }

    /**
     * 判斷sdcard是否可用
     * @return
     */
    private static boolean isSdAvailable() {
       if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
       {
           return true;
       }
        return false;
    }

    /**
     * 获取缓存的文件夹
     * @return
     */
    public static File getCacheDir(){
        return getDir(CACHE);
    }
}