package handlers;

import client.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

class PreconditionHandler implements LogicalHandler<LogicalMessageContext> {

    public void close(MessageContext mctx) {
    }

    public boolean handleFault(LogicalMessageContext lmctx) {
        return true;
    }

    public boolean handleMessage(LogicalMessageContext lmctx) {
        Boolean outbound
                = (Boolean) lmctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) { // request?
            LogicalMessage msg = lmctx.getMessage();
            System.out.println("@@@ID Handler filtered");
            try {
                JAXBContext jaxbCtx = JAXBContext.newInstance("client");
                Object payload = msg.getPayload(jaxbCtx);
                // Check payload to be sure it's what we want.
                if (payload instanceof JAXBElement) {
                    Object value = ((JAXBElement) payload).getValue();
                    // Three possibilities of interest: GetOne, Edit, or Delete
                    String id = null;
                    boolean toSevenScale, toLetterScale, toHundredScale;
                    toSevenScale = toLetterScale = toHundredScale = false;
                    if (value.toString().contains("ToSevenScale")) {
                        id = ((ToSevenScale) value).getArg0();
                        toSevenScale = true;
                    } else if (value.toString().contains("ToLetterScale")) {
                        id = ((ToLetterScale) value).getArg0();
                        toLetterScale = true;
                    } else if (value.toString().contains("ToHundredScale")) {
                        id = ((ToHundredScale) value).getArg0();
                        toHundredScale = true;
                    } else {
                        return true; // GetAll or Create
                    }
                    // If the request is GetOne, Edit, or Delete and the id is zero,
                    // there is a problem that cannot be fixed.
                    if (toSevenScale || toLetterScale || toHundredScale) {
                        if (id == null) // can't fix
                        {
                            throw new RuntimeException("Scale cannot be empty!");
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}