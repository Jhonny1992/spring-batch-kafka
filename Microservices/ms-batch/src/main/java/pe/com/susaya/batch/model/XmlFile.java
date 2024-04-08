package pe.com.susaya.batch.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlFile {
    @XmlElement(name = "person")
    private XmlPerson xmlPerson;
    @XmlElement(name = "random")
    private Integer random;
    @XmlElement(name = "random_float")
    private Double randomFloat;
    @XmlElement(name = "bool")
    private String bool;
    @XmlElement(name = "date")
    private String date;
    @XmlElement(name = "regEx")
    private String regEx;
    @XmlElement(name = "enum")
    private String enume;



}
