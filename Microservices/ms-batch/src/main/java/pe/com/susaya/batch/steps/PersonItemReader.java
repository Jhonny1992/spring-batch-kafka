package pe.com.susaya.batch.steps;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import pe.com.susaya.batch.model.XmlFile;


    public class PersonItemReader extends StaxEventItemReader<XmlFile> {

        public PersonItemReader() {
            setName("readPersons");
            setStrict(true);
            setFragmentRootElementName("root");
            setUnmarshaller(getUnmarshaller());
        }

        private Jaxb2Marshaller getUnmarshaller() {
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setClassesToBeBound(XmlFile.class);
            return marshaller;
        }
    }

