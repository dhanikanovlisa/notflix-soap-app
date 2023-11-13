package handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
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
            ctx.put("Authorized", authorized);
            if(!authorize(ctx)){
                try{
                    injectSOAPFault(ctx, "Client", "Unauthorized");
                    throw new SOAPFaultException(ctx.getMessage().getSOAPBody().getFault());
                }catch(SOAPException e){
                    e.printStackTrace();
                }
            }
            return authorized;
        }
    }

    private void injectSOAPFault(SOAPMessageContext ctx, String faultCode, String faultString) throws SOAPException{
        SOAPBody soapBody = ctx.getMessage().getSOAPBody();
        soapBody.removeContents();
        SOAPFault soapFault = soapBody.addFault();
        soapFault.setFaultCode(faultCode);
        soapFault.setFaultString(faultString);
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
