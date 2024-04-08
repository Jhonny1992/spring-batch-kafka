package pe.com.susaya.batch.steps;

import org.springframework.batch.item.ItemProcessor;
import pe.com.susaya.batch.model.Person;
import pe.com.susaya.batch.model.XmlFile;

public class PersonItemProccesor implements ItemProcessor<XmlFile, Person> {

    @Override
    public Person process(XmlFile person) {

        Person peas = new Person();
        peas.setFirstName(person.getXmlPerson().getFirstName());
        peas.setLastName(person.getXmlPerson().getLastName());
        peas.setCity(person.getXmlPerson().getCity());
        peas.setCountry(person.getXmlPerson().getCountry());
        peas.setFirstName2(person.getXmlPerson().getFirstName2());
        peas.setLastName2(person.getXmlPerson().getLastName2());
        peas.setEmail(person.getXmlPerson().getEmail());
        peas.setRandom(person.getRandom());
        peas.setRandomFloat(person.getRandomFloat());
        peas.setBool(person.getBool());
        peas.setDates(person.getDate());
        peas.setEnume(person.getEnume());
        peas.setRegEx(person.getRegEx());

        return peas;
    }
}
