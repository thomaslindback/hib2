package org.teel.org.teel.om;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.teel.hib1.Autor;

import java.io.IOException;

/**
 * Created by thomas on 2015-06-29.
 */
public class Main {

    private void run() throws IOException {
        System.out.println("start...");
        ObjectMapper objMapper = new ObjectMapper();

        Autor a = new Autor();
        a.setName("autor");
        String jsonString = objMapper.writeValueAsString(a);
        System.out.println(jsonString);

        Autor aut =  objMapper.readValue(jsonString, Autor.class);
        System.out.println(aut);
    }

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
