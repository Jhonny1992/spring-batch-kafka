package pe.com.susaya.batch.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.susaya.batch.model.Person;
import pe.com.susaya.batch.service.IPersonService;

import java.util.function.Consumer;

@Configuration
public class configKafka {

    @Autowired
    private IPersonService personService;

    @Bean
    public Consumer<String> consumerUpdateBatch(){
        return messageUpdateBatch -> {
            try{
                ObjectMapper objectMapper = new ObjectMapper();
                Person person = objectMapper.readValue(messageUpdateBatch, new TypeReference<Person>() {});
                personService.saveAll(person);
            }catch (Exception e){
                e.getMessage();
            }
        };
    }

}
