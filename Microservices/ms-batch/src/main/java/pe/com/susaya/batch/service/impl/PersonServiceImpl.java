package pe.com.susaya.batch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.susaya.batch.model.Person;
import pe.com.susaya.batch.persistence.IPersonDAO;
import pe.com.susaya.batch.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonDAO personDAO;

    @Override
    public void saveAll(Person personList) {
        personDAO.save(personList);
    }
}
