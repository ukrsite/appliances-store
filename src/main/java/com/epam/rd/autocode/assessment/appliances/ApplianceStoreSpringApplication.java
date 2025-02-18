package com.epam.rd.autocode.assessment.appliances;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import com.microsoft.applicationinsights.connectionstring.ConnectionString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplianceStoreSpringApplication {

    public static void main(String[] args) {
        ApplicationInsights.attach();
        ConnectionString.configure("InstrumentationKey=55197219-9543-4bd0-b707-e90043784e4e;IngestionEndpoint=https://canadacentral-1.in.applicationinsights.azure.com/;LiveEndpoint=https://canadacentral.livediagnostics.monitor.azure.com/;ApplicationId=89f652e5-c479-4f23-b46e-608d1675ff0b");
        SpringApplication.run(ApplianceStoreSpringApplication.class, args);
    }
}
