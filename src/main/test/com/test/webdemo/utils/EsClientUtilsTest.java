package com.test.webdemo.utils;

import com.alibaba.fastjson.JSON;
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
        IndexRequestBuilder index = EsClientUtils.createIndex(esClient, "book", "java", "1");
        IndexResponse indexResponse = EsClientUtils.addDocument(index, JSON.toJSONString(indexMap), XContentType.JSON);
        EsClientUtils.closeClient(esClient);
    }

}