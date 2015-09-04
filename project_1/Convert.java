/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
/**
 *
 * @author Leah
 */
public class Convert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String arff = "arff";
        String csv = "csv";
        File file_to_convert = null;
        File arff_file = null;
        String write_test = "Test if file write works";
        //have to convert content to bytes to write to file
        byte[] write_test_bytes = write_test.getBytes();

        if (args.length != 1) {
            System.err.println("Invalid command line,"
                    + " exactly one argument required");
            System.exit(1);
        }

        file_to_convert = new File(args[0]);
        if (!file_to_convert.exists()) {
            System.out.println("File does not exist");
            System.exit(1);
        }
        String file_to_covert_name = file_to_convert.getName();
        String ext = file_to_covert_name.substring(file_to_covert_name.
                           lastIndexOf(".") + 1, file_to_covert_name.length());
        if (ext != csv) {
            System.out.println("Need CSV file!");
            System.exit(1);
        }
        String base_name = file_to_covert_name.substring(file_to_covert_name.
                                                    lastIndexOf(".") - 1, 0);

        System.out.println("Extension = " + ext);
        // renaming the outputted arff file
        System.out.println("Base name = " + base_name);
        arff_file = new File(base_name + ".arff");

        try (FileOutputStream final_arff = new FileOutputStream(arff_file)){

            // conversion stuff starts here

            // write test string to arff file
            final_arff.write(write_test_bytes);

            // conversion stuff ends here
            final_arff.flush();
            final_arff.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 

    }

}

