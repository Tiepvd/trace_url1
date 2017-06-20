package com.vnptnet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.security.Principal;

/**
 * Created by Vu Duc Tiep on 3/17/2017.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketDefaultConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //config.enableStompBrokerRelay("/topic/", "/queue/");
        config.enableSimpleBroker("/topic/", "/queue/");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/tailfilesep").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(new ChannelInterceptorAdapter() {

            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(message);
                if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
                    Principal userPrincipal = headerAccessor.getUser();
                    System.out.println("on sub "+userPrincipal.getName());
                    if(!validateSubscription(userPrincipal, headerAccessor.getDestination()))
                    {
                        throw new IllegalArgumentException("No permission for this topic");
                    }
                }
                return message;
            }

            private boolean validateSubscription(Principal principal, String topicDestination)
            {
                if (principal == null) {
                    // unauthenticated user
                    return false;
                }
                //logger.debug("Validate subscription for {} to topic {}",principal.getName(),topicDestination);
                //Additional validation logic coming here
                return true;
            }
        });
    }
}
