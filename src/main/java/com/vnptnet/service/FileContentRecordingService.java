package com.vnptnet.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Vu Duc Tiep on 3/17/2017.
 */
@Service
public class FileContentRecordingService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private Logger logger= Logger.getLogger(FileContentRecordingService.class);
    public void sendLinesToTopic(String line) {
//		String finalLine = line.split("##")[1];
		logger.info(line);
        String[] lines = line.split("##");
        for (String line1 : lines) {
            line = line1;
        }
//		this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", line);
//        this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", line.substring(line.indexOf("#"),line.length()) );
        this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", line);
    }
}
