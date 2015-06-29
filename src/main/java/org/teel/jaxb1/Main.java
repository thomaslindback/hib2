package org.teel.jaxb1;

//import javax.xml.bind.JAXBContext;

import org.teel.jaxb1.dom.Department;
import org.teel.jaxb1.dom.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() throws JAXBException {
        System.out.println("run...");


        Department d = new Department();
        d.setName("d1");
        Employee emp = new Employee();
        emp.setName("emp");
        emp.setDepartment(d);
        d.getEmployees().add(emp);

        System.out.println(JAXBContext.newInstance(Department.class).getClass());
        StringWriter sw = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(Department.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(d, System.out);
        jaxbMarshaller.marshal(d, sw);

        // unmarsal
        Unmarshaller u = jaxbContext.createUnmarshaller();
        Department d_um = (Department) u.unmarshal(new StringReader(sw.toString()));
        System.out.println(d_um);
        System.out.println("\t" + d_um.getEmployees().get(0).getDepartment().getName());

    }

}
