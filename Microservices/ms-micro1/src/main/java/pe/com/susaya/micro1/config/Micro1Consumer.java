package pe.com.susaya.micro1.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class Micro1Consumer {

    @Autowired
    private StreamBridge streamBridge;

    @Bean
    public Consumer<String> consumerBatch(){
        return messageBatch -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map<String, Object> jsonMap = objectMapper.readValue(messageBatch, new TypeReference<Map<String, Object>>() {});
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime date=LocalDateTime.now();
                jsonMap.put("last_update", "MICROSERVICES_PROCESS_01" + formatter.format(date));
                String modifiedJson = objectMapper.writeValueAsString(jsonMap);
                publicUpdateBatch(modifiedJson);
            } catch (JsonProcessingException e) {
                log.error("Error al procesar el mensaje JSON: {}", e.getMessage());
            }
        };
    }

    public void publicUpdateBatch(String json){
        this.streamBridge.send("consumerUpdateBatch", json);
        this.streamBridge.send("consumerUpdateBatch-in-0", json);
        this.streamBridge.send("consumerUpdateBatch-out-0", json);
    }
}
