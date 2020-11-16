package com.ubaid.concurrency.waintAndNotifyDemo2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Demo {

    List<WebLink> webLinks = Arrays.asList(
            new WebLink("Link-1", null),
            new WebLink("Link-2", null),
            new WebLink("Link-3", null),
            new WebLink("Link-4", null),
            new WebLink("Link-5", null),
            new WebLink("Link-6", null),
            new WebLink("Link-7", null),
            new WebLink("Link-8", null),
            new WebLink("Link-9", null),
            new WebLink("Link-10", null),
            new WebLink("Link-11", null),
            new WebLink("Link-12", null),
            new WebLink("Link-13", null),
            new WebLink("Link-14", null),
            new WebLink("Link-15", null),
            new WebLink("Link-16", null),
            new WebLink("Link-17", null),
            new WebLink("Link-18", null),
            new WebLink("Link-19", null),
            new WebLink("Link-20", null));

    public static void main(String[] args) {
        log.info("App Start");
        Demo demo = new Demo();
        demo.app();

    }

    @SneakyThrows
    public void app() {
        for (WebLink webLink : webLinks) {
            Thread downloader = new Thread(new Downloader(webLink), "Downloader-" + webLink.getLink());
            Thread indexer = new Thread(new Indexer(webLink), "Indexer-" + webLink.getLink());
            downloader.start();
            indexer.start();
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        //execute two threads
        //both have access a thread safe shared queue
        //both threads have infinite loop
        //one is acting like producer
        //while the other is acting like consumer
    }


    @Data
    @AllArgsConstructor
    static class WebLink {
        String link;
        String data;
    }

    static class Downloader implements Runnable {

        private final WebLink page;

        Downloader(WebLink page) {
            this.page = page;
        }

        @Override
        @SneakyThrows
        public void run() {
            Thread.sleep(new Random().nextInt(5000));
            page.setData("Page#" + new Random().nextInt(122222));
            log.info("Downloading {} ", page);
            log.info("Notifying {}............", page);
            synchronized (page) {
                page.notifyAll();
            }
        }
    }

    static class Indexer implements Runnable {

        final WebLink page;

        Indexer(WebLink page) {
            this.page = page;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (page.getData() == null) {
                log.info("Waiting for Data {} ............", page);
                synchronized (page) {
                    page.wait();
                }
                log.info("Indexing {}", page);
            }
        }
    }
}