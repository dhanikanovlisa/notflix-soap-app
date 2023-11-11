package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import model.ApiKeysModel;
import model.LoggingModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class BaseWS {
    @Resource
    WebServiceContext wsContext;
    public boolean verifyApiKey() {
        try {

            MessageContext messageContext = wsContext.getMessageContext();
            Map<String, List<String>> requestHeaders = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
            String api_key = getApiKeyFromHeaders(requestHeaders);

            boolean trueApi = ApiKeysModel.getInstance().verifyApiKey(api_key);

            return trueApi;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getApiKeyFromHeaders(Map<String, List<String>> headers) {
        List<String> apiKeyHeader = headers.get("API-Key");

        if (apiKeyHeader != null && !apiKeyHeader.isEmpty()) {
            return apiKeyHeader.get(0);
        } else {
            return null;
        }
    }

    public String insertLog(String description, String ip, String endpoint){
        try {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            return LoggingModel.getInstance().createLog(description, ip, endpoint, ts);
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
