package com.ubaid.concurrency.waitNotifyDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IndexerImp implements Indexer{

    private final String page;

    public IndexerImp(String page) {
        this.page = page;
    }

    @Override
    public void run() {
        index();
    }

    @Override
    public String index() {
        log.info("Indexing is start for " + page);
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
//            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Indexing is finished for " + page);
        return "indexed " + page;
    }
}
