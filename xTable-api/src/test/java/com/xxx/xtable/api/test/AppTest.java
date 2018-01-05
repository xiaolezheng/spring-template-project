package com.xxx.xtable.api.test;

import com.xxx.xtable.api.ClientFactory;
import com.xxx.xtable.api.DebugClient;
import com.xxx.xtable.api.Result;
import com.xxx.xtable.api.XTableDataClient;
import com.xxx.xtable.api.XTableMetaClient;
import com.xxx.xtable.api.model.XTable;
import com.xxx.xtable.api.model.XTableData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppTest {
    private static final String ACCESS_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3NzU5NzQxNTEzMzMxNTgyIiwic3ViIjoiNzc1OTczIiwiYWlkIjo3NzU5NzQsInRpZCI6Nzc1OTU3LCJpYXQiOjB9.--FStbCppSfouhDvhcEfFy-lJZiWJVhexOBxbIBakIc";

    static {
        ClientFactory.init(ClientFactory.DEV_HOST);
    }

    public static void main(String[] args) {
        DebugClient debugClient = ClientFactory.get(DebugClient.class);

        Map<String, Object> request = new HashMap<>();
        request.put("tenantId", 11032L);

        System.out.println(debugClient.post(ACCESS_TOKEN, request));

        System.out.println(debugClient.get(ACCESS_TOKEN));

        XTableDataClient dataClient = ClientFactory.get(XTableDataClient.class);
        Result<XTableData> result = dataClient.find(ACCESS_TOKEN, "5a4381cbe964f100127b3108", 912903L);
        if (result.getStatus() == 1) {
            System.out.println(result.getData());
            System.out.println(result.getData().getXTableId());
        } else {
            System.out.println(result.getMessage());
        }

        Result<List<XTableData>> listResult = dataClient.find(ACCESS_TOKEN, "5a4381cbe964f100127b3108");
        if (listResult.succeed()) {
            System.out.println(listResult.getData().size());
            System.out.println(result.getData());
        } else {
            System.out.println(result.getMessage());
        }

        XTableMetaClient metaClient = ClientFactory.get(XTableMetaClient.class);
        Result<XTable> xTableResult = metaClient.find(ACCESS_TOKEN, "5a4c6072e6324bc698e64a58");
        if(xTableResult.succeed()){
            System.out.println(xTableResult.getData());
            System.out.println(xTableResult.getData().getId());
        }
    }
}
