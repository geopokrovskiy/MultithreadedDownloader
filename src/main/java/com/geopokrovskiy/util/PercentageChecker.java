package com.geopokrovskiy.util;

public class PercentageChecker implements ProgressChecker{
    @Override
    public void check(long remain, long total) {
        if (remain > total) {
            throw new IllegalArgumentException();
        }
        int maxBareSize = 10; // 10unit for 100%
        int remainPercent = (int) ((100 * remain) / total) / maxBareSize;
        char defaultChar = '-';
        String icon = "*";
        String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
        String bareDone = "[" + icon.repeat(remainPercent);
        String bareRemain = bare.substring(remainPercent);
        System.out.print("\r" + bareDone + bareRemain + " " + remainPercent * 10 + "%");
        if (remain == total) {
            System.out.print("\n");
        }
    }
}
