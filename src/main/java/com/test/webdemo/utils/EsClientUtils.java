package com.test.webdemo.utils;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author by Dawei on 2018/5/30.
 */
public class EsClientUtils {

    private static final String ES_SERVER_HOST = GetPropertyUtil.getPropertiesInfoString("elasticsearch.properties", "hostAddress", "192.168.111.21");
    private static final Integer ES_SERVER_PORT = GetPropertyUtil.getPropertiesInfoInt("elasticsearch.properties", "esPort", "9200");


    /**
     * 创建连接
     */
    public static TransportClient getESClient() {
        TransportClient transportClient = null;
        try {
            //设置集群信息
            Settings settings = Settings.builder().put("cluster.name","my-application").build();
            //创建Client
            transportClient =
                    new PreBuiltTransportClient(settings)
                            .addTransportAddress(new TransportAddress(InetAddress.getByName(ES_SERVER_HOST), ES_SERVER_PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return transportClient;
    }

    /**
     * 关闭连接
     */
    public static void closeClient(TransportClient transportClient) {
        if(transportClient != null) {
            transportClient.close();
        }
    }

    /**
     * 创建索引
     */
    public static IndexRequestBuilder createIndex(TransportClient transportClient, String index1, String index2, String indexId) {
        /*
         * 创建索引 参数: 索引一级分组，索引二级分组， [索引ID]
         */
        IndexRequestBuilder indexRequest;
        if(indexId != null) {
            indexRequest = transportClient.prepareIndex(index1, index2, indexId);
        } else {
            indexRequest = transportClient.prepareIndex(index1, index2);
        }
        return indexRequest;
    }

    /**
     * 添加文档
     * @param indexRequest 索引请求
     * @param source 存储的资源内容
     * @param contentType 资源内容格式类型
     * @return 添加Response
     */
    public static IndexResponse addDocument(IndexRequestBuilder indexRequest, String source, XContentType contentType) {
        /* 添加文档 */
        IndexRequestBuilder documentResponse = indexRequest.setSource(JSON.toJSONString(source), contentType);
        /* 获取结果信息 */
        return indexRequest.get();
    }






}
