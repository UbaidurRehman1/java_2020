package com.ubaid.concurrency.interrupt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class NaiveInterrupt {

    public static void main(String [] args) {

        //download all in separate threads 
        List<WebLink> webLinks = Arrays.asList(
                new WebLink("Link-1", null),
                new WebLink("Link-2", null),
                new WebLink("Link-3", null),
                new WebLink("Link-4", null),
                new WebLink("Link-5", null),
                new WebLink("Link-6", null),
                new WebLink("Link-7", null),
                new WebLink("Link-8", null),
                new WebLink("Link-9", null));


        ExecutorService downloaderService = Executors.newCachedThreadPool(new DownloadThreadFactory());
        ExecutorService indexerService = Executors.newCachedThreadPool(new IndexThreadFactory());

        webLinks.forEach(webLink -> {
            try {

                Future<?> future =  downloaderService.submit(new Downloader(webLink));
                downloaderService.awaitTermination(2, TimeUnit.SECONDS);
                future.cancel(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            indexerService.execute(new Indexer(webLink));
        });

    }

    @Data
    @AllArgsConstructor
    static class WebLink {
        String link;
        String data;
    }


    @AllArgsConstructor
    @Slf4j
    static class Downloader implements Runnable {

        WebLink link;
        private final Random random = new Random();

        @Override
        public void run() {
            try {
                log.info("Downloading weblink {} in thread {}", link, Thread.currentThread().getName());
                int timeToSleep = random.nextInt(5000);
                Thread.sleep(timeToSleep);
                link.setData("This is data downloaded after " + timeToSleep/1000.0 + "s");
            } catch (InterruptedException exp) {
                log.error("{} interrupted due to timeout", Thread.currentThread().getName());
            }
        }
    }

    @Slf4j
    @AllArgsConstructor
    static class Indexer implements Runnable {

        WebLink link;
        private final Random random = new Random();

        @Override
        public void run() {
            try {
                if (link.getData() != null) {
                    log.info("indexing web-link {} in thread {}", link, Thread.currentThread().getName());
                } else {
                    log.error("No data in weblink {}", link);
                }
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException exp) {
                log.error("{} interrupted due to timeout", Thread.currentThread().getName());
            }
        }
    }
}

class DownloadThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "DownloadThreadFactory");
    }
}

class IndexThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "IndexThreadFactory");
    }
}
