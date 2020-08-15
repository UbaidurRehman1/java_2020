package com.ubaid.concurrency.waitNotifyDemo;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class App {

    public static void main(String [] args) {
        App app = new App();
        List<String> links = Arrays.asList("link1", "link2", "link3", "link4", "link5", "link6", "link7", "link8", "link9", "link10", "link11", "link12", "link13",
                "link14", "link15", "link16", "link17", "link18", "link19", "link20", "link21", "link22", "link23", "link24", "link25", "link26", "link27", "link28", "link29", "link30");
        long start = System.nanoTime();
//        app.multi(links);
        app.single(links);
        long end = System.nanoTime();

        long totalTime = end - start;
        log.info("Time Elapsed: " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS) + " Seconds");
    }

    public void single(List<String> links) {
        Queue<String> pages = new ArrayDeque<>();

        for (String link: links) {
            Downloader downloader = new DownloaderImp(link, pages);
            String page = downloader.download();
            Indexer indexer = new IndexerImp(page);
            String indexedPage = indexer.index();
            log.info("Indexed Page: " + indexedPage);
        }
    }

    public void multi(List<String> links) {
        Queue<String> pages = new ArrayDeque<>();

        for (String link: links) {
            Downloader downloader = new DownloaderImp(link, pages);
            Thread downloadingThread = new Thread(downloader, "Downloader: " + link);
            downloadingThread.start();
        }

        while (!waitForDownloader(pages)) {
            String page = pages.poll();
            Indexer indexer = new IndexerImp(page);
            Thread indexerThread = new Thread(indexer, "Indexer: " + page);
            indexerThread.start();
        }

    }

    private boolean waitForDownloader(Queue<String> pages) {
        if (pages.isEmpty()) {
            try {
                log.info("Waiting for pages:");
                log.info("Pages: " + pages.toString());
                timedWait(6000);
                if (pages.isEmpty()) {
                    return true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private synchronized void timedWait(long time) throws InterruptedException {
        wait(time);
    }
}
