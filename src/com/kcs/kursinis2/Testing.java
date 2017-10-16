package com.kcs.kursinis2;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;


public class Testing {
    public static void main(String[] args) {
        String a = "14:30";
        String b = "16:30";

        System.out.println(Utils.timeDif(a,b));
    }
}
