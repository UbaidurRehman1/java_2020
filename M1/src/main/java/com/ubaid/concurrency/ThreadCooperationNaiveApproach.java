package com.ubaid.concurrency;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class ThreadCooperationNaiveApproach {

    public static void main(String [] args) {
        ThreadCooperationNaiveApproach threadCooperation = new ThreadCooperationNaiveApproach();
        threadCooperation.add(threadCooperation.createWebLink(2003, "JNDI Datasource HOW-TO", "http://tomcat.apache.org/tomcat-6.0-doc/jndi-datasource-examples-howto.html", "http://tomcat.apache.org"));
        threadCooperation.add(threadCooperation.createWebLink(2004, "Virtual Hosting and Tomcat", "http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));
        threadCooperation.go();
    }



    private Deque<WebLink> queue = new ArrayDeque<>();

    private static class WebLink {
        private long id;
        private String title;
        private String url;
        private String host;

        private volatile String htmlPage;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getHtmlPage() {
            return htmlPage;
        }

        public void setHtmlPage(String htmlPage) {
            this.htmlPage = htmlPage;
        }
    }

    private static class Downloader implements Runnable {
        private WebLink webLink;

        public Downloader(WebLink webLink) {
            this.webLink = webLink;
        }

        @Override
        public void run() {
            try {
                String htmlPage = HttpConnect.download(webLink.getUrl());
                webLink.setHtmlPage(htmlPage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Indexer implements Runnable {
        private WebLink webLink;

        public Indexer(WebLink webLink) {
            this.webLink = webLink;
        }

        @Override
        public void run() {
            while (true) {
                String htmlPage = webLink.getHtmlPage();
                if (htmlPage != null) {
                    index(htmlPage);
                    break;
                } else {
                    System.out.println(webLink.getId() + " Not Yet downloaded");
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void index(String page) {
            if (page != null) {
                System.out.println("\nIndexed: " + webLink.getId() + "\n");
            }
        }
    }

    public void go() {
        while (queue.size() > 0) {
            WebLink webLink = queue.remove();
            Thread downloaderThread = new Thread(new Downloader(webLink));
            Thread indexerThread = new Thread(new Indexer(webLink));
            downloaderThread.start();
            indexerThread.start();
        }
    }

    public void add(WebLink webLink) {
        queue.add(webLink);
    }

    public WebLink createWebLink(long id, String title, String url, String host) {
        WebLink webLink = new WebLink();
        webLink.setId(id);
        webLink.setTitle(title);
        webLink.setUrl(url);
        webLink.setHost(host);
        return webLink;
    }
}
