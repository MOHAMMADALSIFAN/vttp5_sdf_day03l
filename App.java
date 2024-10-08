import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.print.DocFlavor.INPUT_STREAM;

import people.Person;
import service.CSVManagement;

public class App {

    public static void main(String[] args) throws IOException{

        // arg[0] arg[1] arg[2]
        // arg[0] "c:\data"
        // arg[1] myfile\txt
        //windows: c:\data\myfile.txt

       String dirPath = args[0];
        String fileName = args[1];
        String dirPathFileName = dirPath + File.separator +  fileName;

        // check if a directory exists
        // if directory doesn not exist, create a directory
        File directory = new File(dirPath);

        if (directory.exists()) {
            System.out.println("Directory" + directory.toString() + "had already been created.");

        } else {
            directory.mkdir();
        }
        // check if the file exists
        // if file does not exist, create the file
        File file = new File(dirPathFileName);

        if (file.exists()){
            System.out.println("File" + file.toString() + "already created");
        } else {
            file.createNewFile();
        } 

        
        // Example 1
        //use Filewiter 
        // 1. Use console to read in a string of text (sentence)
        // 2. use Filewriter to write the content to the file


        /*Console console = System.console();
        String keyBoardInput = console.readLine("Enter/Input a sentence: ");
        FileWriter fw = new FileWriter(file, true);
        fw.write(keyBoardInput);
        fw.flush();
        fw.close();

        //use FileReader to reaad the file
        FileReader fr = new FileReader(file);
        int dataRead = fr.read();

        while(dataRead != -1) {
            System.out.println((char) dataRead);
            dataRead = fr.read();
        }
        fr.close();*/

        //Example 2
        // use BufferedWriter to write to file
        //Console console = System.console();
        //String keyBoardInput = console.readLine("Enter/Input a sentence: ");
       /*  FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(keyBoardInput);
        bw.flush();
        bw.close();

        //use BufferedRead to read the file content
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        fr.close(); */

        //Example 3
        //Use FileOutputStream to write to file 
        /* FileOutputStream fos = new FileOutputStream(file, true);
        byte[] byteContent = keyBoardInput.getBytes();
        fos.write(byteContent);
        fos.flush();
        fos.close();

        //use FileInputStream to read the file content
        FileInputStream fis = new FileInputStream(file);
        int contentRead = 0;
        while ((contentRead = fis.read()) != -1) {
            System.out.println((char) contentRead);
        } 
        fis.close(); */

        // Example 4 (Decorator Pattern)
        // use FileoutputStream and GzipOuputStream to write to file
        /* FileOutputStream fos = new FileOutputStream(file);
        GZIPOutputStream gos = new GZIPOutputStream(fos);
        byte[] contents = keyBoardInput.getBytes();
        gos.write(contents);
        gos.flush();
        gos.close();
        fos.close();


        //use FileInputStream and GzipInputStream to read the content
        FileInputStream fis = new FileInputStream(file);
        GZIPInputStream gis = new GZIPInputStream(fis);
        int gisContent = 0;
        while ((gisContent = gis.read()) != -1 ) {
            System.out.println((char) gisContent);
        }
        gis.close();
        fis.close();
 */     
        List<Person> persons = new ArrayList<>();

        CSVManagement csv = new CSVManagement();
        csv.readCSV(dirPathFileName);


        //menu
        //1. Enter new Person details
        //2. Save to file (Prompt for new csv file name)
        //3. Quit and terminate program

        Console consoleSelection = System.console();
        Integer selection = 0;
        while (selection !=3){
            System.out.println("1. Enter new Person details: ");
            System.out.println("2. Save to new csv file: ");
            System.out.println("3. Quit and terminate program: ");
            selection = Integer.parseInt(consoleSelection.readLine(">>>>>>"));

            switch (selection) {
                case 1:
                    Console con1 = System.console();
                    String personName = con1.readLine("Enter Person Name: ");
                    String personRegion = con1.readLine("Enter Region Name: ");
                    String personYOB = con1.readLine("Enter Year of Birth: ");

                    Person p = new Person (personName, personRegion, Integer.parseInt(personYOB));
                    persons.add(p);
                    
                    break;
                case 2:
                    Console con2 = System.console();
                    String newFileName = con2.readLine("Enter a csv file to save ");

                    csv.writeCSV(dirPath + File.separator + newFileName, persons);
                    break;
            
                default:
                    break;
            }


        }

    }
}

