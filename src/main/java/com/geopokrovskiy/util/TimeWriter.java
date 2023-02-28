package com.geopokrovskiy.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeWriter extends Thread {

    private DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String progressFileName;

    public TimeWriter(String progressFileName) {
        this.progressFileName = progressFileName;
    }

    @Override
    public void run() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(progressFileName))) {
            LocalDateTime localDateTime;
            while (true) {
                localDateTime = LocalDateTime.now();
                String str = this.simpleDateFormat.format(localDateTime);
                bufferedWriter.write(str + "\n");
                bufferedWriter.flush();
                Thread.sleep(10000);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("The TimeWriter has been stopped!");
        }
    }
}
