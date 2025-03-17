package com.fakestoreapi.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report {

    protected static ExtentReports extent;

    public void Reports (){

        String path ="target/reports/Apis.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        extent = new ExtentReports();
        extent.attachReporter(spark);



    }


}
