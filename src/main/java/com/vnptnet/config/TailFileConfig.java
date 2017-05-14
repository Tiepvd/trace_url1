package com.vnptnet.config;

import com.vnptnet.service.FileContentRecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.file.tail.ApacheCommonsFileTailingMessageProducer;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by Vu Duc Tiep on 3/17/2017.
 */
//@Configuration
public class TailFileConfig {
    private SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy/MM/dd");
    @Autowired
    private FileContentRecordingService fileContentRecordingService;

    public MessageProducerSupport fileContentProducer() {
//		String filePath= "/home/firefly/log/10.1.31.21/Log/";
        String filePath= "D:/tmp/";

        ApacheCommonsFileTailingMessageProducer tailFileProducer = new ApacheCommonsFileTailingMessageProducer();
//		tailFileProducer.setFile(new File(new File(System.getProperty("java.io.tmpdir")), "quotes.txt"));

        tailFileProducer.setFile(new File(filePath+"messages-20170514.log"));
        return tailFileProducer;
    }

    @Bean
    public IntegrationFlow tailFilesFlow() {
        return IntegrationFlows.from(this.fileContentProducer())
                .handle("fileContentRecordingService", "sendLinesToTopic")
                .get();
    }
}
