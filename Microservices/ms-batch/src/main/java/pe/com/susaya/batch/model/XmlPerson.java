package pe.com.susaya.batch.model;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlPerson {

    @XmlAttribute( name = "firstname")
    private String firstName;
    @XmlAttribute( name = "lastname")
    private String lastName;
    @XmlAttribute( name = "city")
    private String city;
    @XmlAttribute( name = "country")
    private String country;
    @XmlAttribute( name = "firstname2")
    private String firstName2;
    @XmlAttribute( name = "lastname2")
    private String lastName2;
    @XmlAttribute( name = "email")
    private String email;

}
