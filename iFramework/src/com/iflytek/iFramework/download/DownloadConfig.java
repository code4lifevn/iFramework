package com.iflytek.iFramework.download;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by xhrong on 2014/6/28.
 */
public class DownloadConfig {

    private String downloadSavePath;

    private int maxDownloadThread;

    private int retryTime;

    private DownloadProvider provider;

    private DownloadTaskIDCreator taskIdCreator;

    private DownloadConfig() {
        downloadSavePath = Environment.getExternalStorageDirectory().getPath()  + File.separator + "download";
        maxDownloadThread = 2;
        retryTime = 2;
        taskIdCreator = new MD5DownloadTaskIDCreator();
    }

    protected void setProvider(DownloadProvider provider){
        this.provider=provider;
    }

    public String getDownloadSavePath() {
        return downloadSavePath;
    }

    public int getMaxDownloadThread() {
        return maxDownloadThread;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public DownloadProvider getProvider(DownloadManager manager) {
        if(provider == null) {
            provider = SqlLiteDownloadProvider.getInstance(manager);
        }
        return provider;
    }

    public DownloadTaskIDCreator getTaskIdCreator() {
        return taskIdCreator;
    }

    public static DownloadConfig getDefaultDownloadConfig() {
        DownloadConfig config = new DownloadConfig();
        return config;
    }

    public static class Builder {

        private DownloadConfig config;

        public Builder(Context context) {
            config = new DownloadConfig();
        }

        public DownloadConfig build() {
            return config;
        }

        public Builder setDownloadSavePath(String downloadSavePath) {
            config.downloadSavePath = downloadSavePath;
            return this;
        }

        public Builder setMaxDownloadThread(int maxDownloadThread) {
            config.maxDownloadThread = maxDownloadThread;
            return this;
        }

        public Builder setRetryTime(int retryTime) {
            config.retryTime = retryTime;
            return this;
        }

        public Builder setDownloadProvider(DownloadProvider provider) {
            config.provider = provider;
            return this;
        }

        public Builder setDownloadTaskIDCreator(DownloadTaskIDCreator creator) {
            config.taskIdCreator = creator;
            return this;
        }
    }
}