package com.fakestoreapi.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import net.serenitybdd.screenplay.Actor;

public class Constant {

    public Constant() {
    }

        public static final Actor Tester = Actor.named("Tester");
        public static final String BASE_URL = "https://fakestoreapi.com";
        public static final String REPORT_PATH = "target/reports/ApisReporte.html";

        public static final ExtentReports extent;
        public static final ExtentSparkReporter spark;

        static {
            extent = new ExtentReports();
            spark = new ExtentSparkReporter(REPORT_PATH);
            extent.attachReporter(spark);
        }
}
