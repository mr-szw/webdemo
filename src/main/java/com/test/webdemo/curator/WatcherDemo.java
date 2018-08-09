package com.test.webdemo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * @author by Dawei on 2018/8/9.
 */
public class WatcherDemo {



    private static CuratorFramework curatorFrameworkClient;


    private static TreeCache treeCache;


    public static void main(String[] args) {

        //初始化 Client
        curatorFrameworkClient = CuratorFrameworkFactory.builder().connectString("host:port")
                .connectionTimeoutMs(3000).retryPolicy(new ExponentialBackoffRetry(10))
                .build();

        //启动Curator
        curatorFrameworkClient.start();

        treeCache = new TreeCache(curatorFrameworkClient, "/Tomcat");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {

            }
        });
    }
}
