package handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import model.ApiKeysModel;

import java.util.Map;
import java.util.Set;
import java.util.List;

public class KeyHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders(){
        return null;
    }

    @Override
    public void close(MessageContext ctx){

    }

    @Override
    public boolean handleFault(SOAPMessageContext ctx){
        return true;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext ctx){
        boolean outbound = (boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(outbound){
            return true;
        }
        else{
            boolean authorized = authorize(ctx);
            if(!authorize(ctx)){
                try{
                    SOAPFault soapFault = ctx.getMessage().getSOAPPart().getEnvelope().getBody().addFault();
                    soapFault.setFaultCode(new QName("http://example.com/namespace", "Client", "env"));
                    soapFault.setFaultString("Unauthorized access");
                    throw new SOAPFaultException(soapFault);
                }catch(SOAPException e){
                    e.printStackTrace();
                }
            }
            return authorized;
        }
    }

    private boolean authorize(SOAPMessageContext ctx){
        try{
            @SuppressWarnings("unchecked")
            Map<String, List<?>> headers = (Map<String, List<?>>) ctx.get(MessageContext.HTTP_REQUEST_HEADERS);

            if (headers.containsKey("Api-key")){
                String keyHeader = (String) headers.get("Api-key").get(0);
                return ApiKeysModel.getInstance().verifyApiKey(keyHeader);
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
