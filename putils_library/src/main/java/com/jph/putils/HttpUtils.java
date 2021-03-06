package com.jph.putils;

import android.content.Context;
import com.jph.putils.http.DownloadHandler;
import com.jph.putils.http.HttpRequest;
import com.jph.putils.http.HttpHandler;
import com.jph.putils.http.callback.RequestCallBack;
import com.jph.putils.http.entity.BaseResponseInfo;
import com.jph.putils.exception.HttpException;
import com.jph.putils.http.entity.DownLoadAction;

/**
 * 1.网络操作工具类
 * 2.多线程断点下载工具类，提供下载，暂停，删除，以及重置的方法
 * Author: JPH
 * Date: 2015/10/10 0010 16:55
 */
public class HttpUtils {
//    public static String cookie;
    public HttpUtils() {}
    /**
     * 发送请求
     * @param request 请求实体
     * @param callBack 请求回调
     */
    public void send(HttpRequest request,RequestCallBack callBack) {
        try {
            new HttpHandler(request,callBack).execute();
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onFailure(new HttpException(new BaseResponseInfo(),e.toString()));
        }
    }

    /**
     * 多线程断点下载，提供下载，暂停，删除，以及重置的方法
     * @param url 文件下载路径
     * @param target 本地保存路径
     * @param threadCount 线程数
     * @param context
     * @param callBack 下载回调
     * @return DownloadHandler,提供了暂停、开始、重置、删除下载控制接口
     */
    public DownLoadAction download(String url, String target,int threadCount,Context context,RequestCallBack callBack){
        DownLoadAction handler=null;
        try {
            handler=new DownloadHandler(url,target,threadCount,context,callBack);
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onFailure(new HttpException(new BaseResponseInfo(),e.toString()));
        }
        return handler;
    }
    /**
     * 多线程断点下载，提供下载，暂停，删除，以及重置的方法
     * @param url 文件下载路径
     * @param target 本地保存路径
     * @param context
     * @param callBack 下载回调
     * @return DownloadHandler,提供了暂停、开始、重置、删除下载控制接口
     */
    public DownLoadAction download(String url, String target,Context context,RequestCallBack callBack){
        DownLoadAction handler=null;
        try {
            handler=new DownloadHandler(url,target,context,callBack);
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onFailure(new HttpException(new BaseResponseInfo(),e.toString()));
        }
        return handler;
    }
}


