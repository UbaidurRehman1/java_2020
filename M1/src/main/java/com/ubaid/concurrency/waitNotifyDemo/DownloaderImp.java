package com.ubaid.concurrency.waitNotifyDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DownloaderImp implements Downloader{

    private final String link;
    private final Queue<String> queue;

    public DownloaderImp(String link, Queue<String> queue) {
        this.link = link;
        this.queue = queue;
    }

    @Override
    public void run() {
        String page = download();
        queue.add(page);
    }

    private synchronized void _notify() {
        notify();
    }

    @Override
    public String download() {
        try {
            log.info("Downloading: " + link);
            TimeUnit.SECONDS.sleep(new Random().nextInt(20));
//            TimeUnit.SECONDS.sleep(1);
            _notify();
            log.info("Downloading Finished: " + link);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return link;
    }
}
