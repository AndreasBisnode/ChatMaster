package common;



import com.fasterxml.jackson.databind.ObjectMapper;
import common.chatmaster.service.ChatAdminService;
import common.chatmaster.service.ChatService;
import common.messagebucket.repository.ChatMessageRepository;
import common.messagebucket.repository.ChatMessageRepositoryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;



@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
