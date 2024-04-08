package pe.com.susaya.batch.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import pe.com.susaya.batch.model.Person;


@Slf4j
public class PersonItemWriter implements ItemWriter<Person> {

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        for (Person person : chunk) {
            String personJson = objectMapper.writeValueAsString(person);
            publicBatch(personJson);
        }
    }

    public void publicBatch(String json){
        this.streamBridge.send("consumerBatch", json);
        this.streamBridge.send("consumerBatch-in-0", json);
        this.streamBridge.send("consumerBatch-out-0", json);
    }
}
