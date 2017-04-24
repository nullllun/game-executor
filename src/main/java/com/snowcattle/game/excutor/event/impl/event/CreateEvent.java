package com.snowcattle.game.excutor.event.impl.event;

import com.snowcattle.game.excutor.event.CycleEvent;
import com.snowcattle.game.excutor.event.EventParam;
import com.snowcattle.game.excutor.event.EventType;
import com.snowcattle.game.excutor.utils.Loggers;

import java.io.Serializable;

/**
 * Created by jiangwenping on 17/1/11.
 *  dispatch thread使用
 */
public class CreateEvent<ID extends Serializable> extends CycleEvent {

    public CreateEvent(EventType eventType,ID eventId,  EventParam... parms){
//        setEventType(eventType);
//        setParams(parms);
        super(eventType, eventId, parms);
    }

    public void call() {
        if(Loggers.utilLogger.isDebugEnabled()){
            EventParam[] eventParams = getParams();
            Loggers.utilLogger.debug("create event " + eventParams[0].getT());
        }

    }
}
