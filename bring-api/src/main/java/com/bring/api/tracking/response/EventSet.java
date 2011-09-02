package com.bring.api.tracking.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventSetType", propOrder = {
    "events"
})
public class EventSet {

    @XmlElement(name = "Event", required = true)
    protected List<Event> events;

    public List<Event> getEvents() {
        if (events == null) {
            events = new ArrayList<Event>();
        }
        return new ArrayList<Event>(this.events);
    }
    
    public Event getEvent(int i) {
        return events.get(i);
    }

}
