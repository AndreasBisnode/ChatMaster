package common.config;



import com.fasterxml.jackson.databind.ObjectMapper;
import common.chatmaster.service.ChatAdminService;
import common.chatmaster.service.Impl.ChatAdminServiceMapImpl;
import common.chatmaster.service.ChatService;
import common.chatmaster.service.Impl.ChatServiceImpl;
import common.messagebucket.repository.ChatMessageRepository;
import common.messagebucket.repository.ChatMessageRepositoryMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andgra on 2015-06-02.
 */
@Configuration
public class AppConfig {
    @Bean
    public ChatMessageRepository chatMessageRepository(){
        return new ChatMessageRepositoryMap();
    }
    @Bean
    public ChatAdminService chatAdminService(){
        return new ChatAdminServiceMapImpl();
    }
    @Bean
    public ChatService chatService(){
        return new ChatServiceImpl(chatMessageRepository(), chatAdminService());
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
