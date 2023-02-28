package com.geopokrovskiy.services;

import com.geopokrovskiy.repository.Repository;
import com.geopokrovskiy.util.PercentageChecker;
import com.geopokrovskiy.util.TimeWriter;

import java.io.IOException;
import java.net.MalformedURLException;

public class LoaderService {
    private String link;
    private String outputFileName;
    private String progressFileName;

    public LoaderService(String link, String outputFileName, String progressFileName)  {
        this.link = link;
        this.outputFileName = outputFileName;
        this.progressFileName = progressFileName;
    }

    /**
     * There are 2 parallel threads in the load service creation method. The first thread should create
     * object repository and search for data from the server in the file, and the second thread should
     * within 10 seconds, record the statistics: the current request and
     * time until the first thread finishes its work
     * @come back
     */
    public boolean load(String link) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Repository repository = new Repository(link, new PercentageChecker());
                    repository.load(outputFileName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new TimeWriter(this.progressFileName);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.interrupt();
        } catch (InterruptedException e) {
            System.out.println("The TimeWriter has been stopped!");
        }

        return true;
    }
}
