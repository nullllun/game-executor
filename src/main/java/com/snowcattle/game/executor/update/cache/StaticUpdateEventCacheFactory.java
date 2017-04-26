package com.snowcattle.game.executor.update.cache;

import com.snowcattle.game.executor.common.utils.Loggers;
import com.snowcattle.game.executor.event.impl.event.UpdateEvent;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by jiangwenping on 17/4/26.
 */
public class StaticUpdateEventCacheFactory {

    public static UpdateEventCacheFactory updateEventCacheFactory;

    public static void start(){
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(1024 * 32);
        genericObjectPoolConfig.setMaxIdle(1024 * 32);
        genericObjectPoolConfig.setMinIdle(1024);
        genericObjectPoolConfig.setSoftMinEvictableIdleTimeMillis(1000L * 30);

        updateEventCacheFactory = new UpdateEventCacheFactory(new UpdateEventPoolFactory(), genericObjectPoolConfig);
    }

    public static void stop(){
        if(updateEventCacheFactory != null) {
            updateEventCacheFactory.close();
        }
    }

    public static UpdateEvent createUpdateEvent(){
        try {
            return updateEventCacheFactory.borrowObject();
        } catch (Exception e) {
            Loggers.errorLogger.error(e.toString(), e);
        }
        return null;
    }

    public static void releaseUpdateEvent(UpdateEvent updateEvent){
            updateEventCacheFactory.returnObject(updateEvent);
    }
}
