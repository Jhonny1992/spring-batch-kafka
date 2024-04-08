package pe.com.susaya.batch.steps;

import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import pe.com.susaya.batch.model.XmlFile;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PersonMultiItemReader extends MultiResourceItemReader<XmlFile> {

    public PersonMultiItemReader(StaxEventItemReader<XmlFile> person) throws IOException {
        setStrict(false);
        setResources(getValidXmlResourcesFromZip());
        setDelegate(person);
    }

    private Resource[] getValidXmlResourcesFromZip() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] zipResources = resolver.getResources("classpath:archivos/example4.zip");
        List<Resource> validXmlResources = new ArrayList<>();

        if (zipResources.length > 0) {
            Resource zipResource = zipResources[0];
            try (ZipInputStream zipInputStream = new ZipInputStream(zipResource.getInputStream())) {
                ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    extracted(validXmlResources, zipInputStream, entry);
                }
            }
        }

        return validXmlResources.toArray(new Resource[0]);
    }

    private void extracted(List<Resource> validXmlResources, ZipInputStream zipInputStream, ZipEntry entry) {
        if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".xml")) {
            try {
                byte[] xmlData = zipInputStream.readAllBytes();
                extracted(validXmlResources, entry, xmlData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void extracted(List<Resource> validXmlResources, ZipEntry entry, byte[] xmlData) {
        if (isValidXml(xmlData)) {
            String safeFileName = entry.getName().replaceAll("[^a-zA-Z0-9.-]", "_");
            Resource xmlResource = new ByteArrayResource(xmlData) {
                @Override
                public String getFilename() {
                    return safeFileName;
                }
            };
            validXmlResources.add(xmlResource);
        } else {
            System.err.println("XML format is invalid: " + entry.getName());
        }
    }

    private boolean isValidXml(byte[] xmlData) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.parse(new ByteArrayResource(xmlData).getInputStream());
            return true;
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            return false;
        }
    }
}
