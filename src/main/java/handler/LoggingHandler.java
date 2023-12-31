package handler;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;


import model.ApiKeysModel;
import model.LoggingModel;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders(){
        return null;
    }

    @Override
    public void close(MessageContext ctx){

    }

    @Override
    public boolean handleFault(SOAPMessageContext ctx){
        insertLog(ctx);
        return true;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext ctx){
        insertLog(ctx);
        return true;
    }

    private void insertLog(SOAPMessageContext ctx){
        try {
            StringBuilder description = new StringBuilder();

            boolean outbound = (boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            boolean authorized = KeyHandler.authorize(ctx);
            if(outbound){
                if(authorized){
                    description.append("Called ");
                }else{
                    description.append("Unauthorized tried to call ");
                }
            }else{
                description.append("Attempting to call ");
            }
            
            QName operation = (QName) ctx.get(MessageContext.WSDL_OPERATION);
            description.append(operation.getLocalPart());

            HttpExchange exchange = (HttpExchange) ctx.get("com.sun.xml.ws.http.exchange");

            String ip = exchange.getRemoteAddress().getAddress().getHostAddress();
            String endpoint = exchange.getRequestURI().toString();
            
            Timestamp ts = new Timestamp(System.currentTimeMillis());

            LoggingModel.getInstance().createLog(description.toString(), ip, endpoint, ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
