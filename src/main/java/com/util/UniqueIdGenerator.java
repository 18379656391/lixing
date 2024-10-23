package com.util;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/8/5
 * 雪花算法
 */
public class UniqueIdGenerator {
    private static final Logger log = LoggerFactory.getLogger(UniqueIdGenerator.class);
    private static final long START_TIME = 1483200000000L;
    private static final int APP_HOST_ID_BITS = 13;
    private static final int WORKER_ID_BITS = 5;
    private static final int SEQUENCE_BITS = 10;
    private static final long MAX_APP_HOST_ID = 8191L;
    private static final long MAX_SEQUENCE = 1023L;
    private static final long APP_HOST_ID_SHIFT = 10L;
    private static final long TIMESTAMP_LEFT_SHIFT = 23L;
    private long appHostId;
    private long lastTimestamp = -1L;
    private long sequence = 0L;
    private static volatile UniqueIdGenerator idGen = null;
    protected static String appHost;

    public static UniqueIdGenerator getInstance() {
        if (idGen == null) {
            Class var0 = UniqueIdGenerator.class;
            synchronized(UniqueIdGenerator.class) {
                if (idGen == null) {
                    idGen = new UniqueIdGenerator(appHost);
                }
            }
        }

        return idGen;
    }

    protected UniqueIdGenerator(String appHostIdStr) {
        if (StringUtils.isBlank(appHostIdStr)) {
            appHostIdStr = this.getRandomAppHostId();
        }

        if (log.isDebugEnabled()) {
            log.debug("UniqueIdGenerator appHostIdStr:'{}'", appHostIdStr);
        }

        this.appHostId = Long.valueOf(appHostIdStr);
    }

    private String getRandomAppHostId() {
        int maxServiceId = 256;
        int minServiceId = (maxServiceId >> 1) + 1;
        int maxWorkerId = 32;
        int minWorkerId = (maxWorkerId >> 1) + 1;
        int serviceId = RandomUtils.nextInt(minServiceId, maxServiceId);
        int workerId = RandomUtils.nextInt(minWorkerId, maxWorkerId);
        int appHostId = serviceId << 5 | workerId;
        if (log.isDebugEnabled()) {
            log.debug("getRandomAppHostId serviceId:'{}', workerId:'{}', appHostId:'{}'", new Object[]{serviceId, workerId, appHostId});
        }

        if ((long)appHostId > 8191L) {
            appHostId = (int)RandomUtils.nextLong(4096L, 8191L);
        }

        if (log.isDebugEnabled()) {
            log.debug("getRandomAppHostId appHostId:'{}'", appHostId);
        }

        return String.valueOf(appHostId);
    }

    public Long nextId() {
        return this.genUniqueId();
    }

    private synchronized long genUniqueId() {
        long current = System.currentTimeMillis();
        if (current < this.lastTimestamp) {
            return this.genTmpUniqueId();
        } else {
            if (current == this.lastTimestamp) {
                this.sequence = this.sequence + 1L & 1023L;
                if (this.sequence == 0L) {
                    current = this.nextMs(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = current;
            if (log.isDebugEnabled()) {
                log.debug("UniqueIdGenerator:appHostId--{}", this.appHostId);
            }

            return current - 1483200000000L << 23 | this.appHostId << 10 | this.sequence;
        }
    }

    private synchronized long genTmpUniqueId() {
        long current = System.currentTimeMillis();
        this.sequence = this.sequence + 1L & 1023L;
        if (this.sequence == 0L) {
            ++this.lastTimestamp;
        }

        if (log.isDebugEnabled()) {
            log.debug("UniqueIdGenerator:appHostId--{}", this.appHostId);
        }

        long id = this.lastTimestamp - 1483200000000L << 23 | this.appHostId << 10 | this.sequence;
        log.warn("UniqueIdGenerator WARNING:now time ({})ms lastTimestamp ({})ms ID({})", new Object[]{current, this.lastTimestamp, id});
        return id;
    }

    private long nextMs(long timeStamp) {
        long current;
        for(current = System.currentTimeMillis(); current <= timeStamp; current = System.currentTimeMillis()) {
        }

        return current;
    }

    public static void setAppHost(String appHost) {
        UniqueIdGenerator.appHost = appHost;
    }
}
