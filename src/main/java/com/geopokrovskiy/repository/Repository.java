package com.geopokrovskiy.repository;

import com.geopokrovskiy.util.ProgressChecker;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Repository{
    private URL url;
    private ProgressChecker progressChecker;

    public Repository(String link, ProgressChecker progressChecker) throws MalformedURLException {
        this.url = new URL(link);
        this.progressChecker = progressChecker;
    }

    public void load(String outputFileName) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        long completeSizeFile = urlConnection.getContentLength(); // total number of bytes in the content
        try(BufferedInputStream input = new BufferedInputStream(urlConnection.getInputStream());
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFileName), 1024)){
            byte[] data = new byte[1024]; // a buffer array of 1kB size
            long downloadedFileSize = 0; // number of downloaded bytes
            int x;
            while((x = input.read(data, 0, 1024)) >= 0){  // reading up to 1024 new bytes to the buffer array
                downloadedFileSize += x; // x is number of downloaded bytes at each iteration
                this.progressChecker.check(downloadedFileSize, completeSizeFile);
                output.write(data, 0, x); // writing all x bytes from the buffer array to the stream
            }
        }
    }
}
