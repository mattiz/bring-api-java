package com.bring.api.shippingguide.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class TraceMessages {
    @XmlElement(name="Message")
    private List<String> messageList = new ArrayList<String>();

    public List<String> getMessages() {
        return new ArrayList<String>(messageList);
    }
    
    public void setMessage(String message) {
        this.messageList.add(message);
    }

    public String getFirstMessage() {
        return messageList.get(0);
    }
}
