package com.vnptnet.config;

import com.vnptnet.service.FileContentRecordingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.file.tail.ApacheCommonsFileTailingMessageProducer;
import org.springframework.integration.file.tail.OSDelegatingFileTailingMessageProducer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vu Duc Tiep on 3/17/2017.
 */
@Configuration
public class TailFileConfig {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private Logger logger = Logger.getLogger(TailFileConfig.class);
    @Autowired
    private FileContentRecordingService fileContentRecordingService;

    public TailFileConfig() {
    }

    public MessageProducerSupport fileContentProducer() {
        String filePath = "/home/firefly/log/10.1.31.21/Log";
//        String filePath = "E:/ftp folder/tmp";
//        String filePath= "D:/tmp/";

        String fileName = "messages-" + simpleDateFormat.format(new Date()) + ".log";
        logger.info("---------------------------file name:" + filePath + fileName);

//        OSDelegatingFileTailingMessageProducer tailFileProducer = new OSDelegatingFileTailingMessageProducer();
        ApacheCommonsFileTailingMessageProducer tailFileProducer = new ApacheCommonsFileTailingMessageProducer();
//		tailFileProducer.setFile(new File(new File(System.getProperty("java.io.tmpdir")), "quotes.txt"));
//        tailFileProducer.setEnableStatusReader(false);
//        tailFileProducer.setOptions("-F -n +0");
        tailFileProducer.setPollingDelay(2000);
        tailFileProducer.setReopen(true);
//        tailFileProducer.setFile(new File(filePath + fileName));
        tailFileProducer.setFile(new File(new File(filePath),fileName));
        return tailFileProducer;
    }

    @Bean
    public IntegrationFlow tailFilesFlow() {
        return IntegrationFlows.from(this.fileContentProducer())
                .handle("fileContentRecordingService", "sendLinesToTopic")
                .get();
    }
}
