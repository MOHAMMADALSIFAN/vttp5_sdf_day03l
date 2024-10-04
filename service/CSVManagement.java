package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ListFormat;
import java.util.ArrayList;
import java.util.List;

import people.Person;

public class CSVManagement {

    //filename: dirPath + file serparator + filename
    public List<Person> readCSV(String filename) throws IOException{
        // use BufferReader
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        List<Person> persons = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null ) {
            System.out.println(line);

            // lineData[0], lineData[1], lineData[2]
            String[] lineData = line.split(",");

            Person p = new Person(lineData[0], lineData[1], Integer.parseInt(lineData[2]));
            persons.add(p);

        }
        br.close();
        fr.close();

        return persons;

    }
    
    public void writeCSV(String filename) throws IOException{
    

        

    }
    
}
