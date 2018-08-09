package com.test.webdemo.utils;

import com.alibaba.fastjson.JSON;
import com.test.webdemo.elasticsearch.EsClientUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EsClientUtilsTest {




    @Test
    public void testMethod() {
        Map<String, String> indexMap = new HashMap<>();
        indexMap.put("name", "EsStudy now");
        indexMap.put("publishDate", new Date().toString());
        indexMap.put("price", "199,3");

        TransportClient esClient = EsClientUtils.getESClient();
        //1、创建索引
        IndexRequestBuilder index = EsClientUtils.createIndex(esClient, "book", "java", "4");
        //2、添加或更新文档
      //  IndexResponse indexResponse = EsClientUtils.addDocument(index, JSON.toJSONString(indexMap), XContentType.JSON);
       // System.out.println("indexResponse " + indexResponse.toString());

        //3、查询文档
        GetResponse documentIndex = EsClientUtils.getDocument(esClient, "book", "java", "4");
        System.out.println("documentIndex  " + documentIndex.toString());

        indexMap.put("name", "天天学习ES");
        IndexResponse indexResponseUpdate = EsClientUtils.addDocument(index, JSON.toJSONString(indexMap), XContentType.JSON);
        System.out.println("indexResponseUpdate " + indexResponseUpdate.toString());

        GetResponse documentUpdate = EsClientUtils.getDocument(esClient, "book", "java", "4");
        System.out.println("documentUpdate  " + documentUpdate.toString());

        //4、删除文档
        DeleteResponse deleteResponse = EsClientUtils.deleteDocument(esClient, "book", "java", "4");
        System.out.println("deleteResponse  " + deleteResponse.toString());


        GetResponse documentResult = EsClientUtils.getDocument(esClient, "book", "java", "4");
        System.out.println("documentResult  " + documentResult.toString());

        //5、关闭客户端
        EsClientUtils.closeClient(esClient);
    }

}