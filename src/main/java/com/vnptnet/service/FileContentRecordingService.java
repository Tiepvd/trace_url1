package com.vnptnet.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by Vu Duc Tiep on 3/17/2017.
 */
@Service
public class FileContentRecordingService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //@Autowired
    //private HttpSession httpSession;

    private Logger logger= Logger.getLogger(FileContentRecordingService.class);
    public void sendLinesToTopic(String line) {
//        System.out.println(httpSession.getAttribute("name").toString()+" (in file content rec)");
		String finalLine = line.split("##")[1];
		logger.info("Line = "+finalLine);
//        String[] lines = line.split("##");
//        for (String line1 : lines) {
//            line = line1;
//        }
//		this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", line);
//        this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", line.substring(line.indexOf("#"),line.length()) );
        String id = finalLine.split("\\|")[0];
        logger.info("Id = "+id);
        this.simpMessagingTemplate.convertAndSend("/topic/tailfiles/"+id, finalLine);
    }
}
