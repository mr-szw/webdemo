package com.test.webdemo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * @author by Dawei on 2018/8/9.
 */
public class WatcherDemo {



    private static CuratorFramework curatorFrameworkClient;


    private static TreeCache treeCache;


    public static void main(String[] args) throws Exception {

        //初始化 Client
        curatorFrameworkClient = CuratorFrameworkFactory.builder()
                .connectString("host:port")    //连接地址
                .connectionTimeoutMs(3000)      //设置超时时间
                .retryPolicy(new ExponentialBackoffRetry(100, 9))   //设置重试机制 退避策略
                .build();

        //启动Curator
        curatorFrameworkClient.start();

        treeCache = new TreeCache(curatorFrameworkClient, "/Tomcat");
        treeCache.start();  //启动监听
        treeCache.getListenable().addListener(( CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) -> {
            /*if(curatorFramework == TreeCacheEvent.Type.CONNECTION_LOST) {
                System.out.println("连接丢失");
            } else if( == TreeCacheEvent.Type.NODE_ADDED) {
                System.out.println("新建连接");
            }*/
        });
    }
}
