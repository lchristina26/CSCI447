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
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File file_to_convert = null;

        // must put header line with data columns at beginning of file
        for (String arg : args) {
            if (arg != null) {
                file_to_convert = new File(arg);
                if (!file_to_convert.exists()) {
                    System.out.println("File does not exist");
                    System.exit(1);
                }
                String file_to_covert_name = file_to_convert.getName();
                String base_name = file_to_covert_name.substring(0, file_to_covert_name.lastIndexOf("."));
                System.out.println("Base name = " + base_name);

                // replace all spaces or tabs with commas
                File tempFile = File.createTempFile("temp", "tmp");
                FileWriter fw = new FileWriter(tempFile);
                Reader fr = new FileReader(arg);
                BufferedReader br = new BufferedReader(fr);

                while(br.ready()) {
                    String line = br.readLine();
                    if(line.contains(" ")) {
                        fw.write(line.replaceAll("^ +| +$|( )+", ",") + "\r\n"); //replace all sequences of spaces with a comma
                    } else if (line.contains("\t")) {
                        fw.write(line.replaceAll("\t", ",") + "\r\n");
                    } else if (line.contains(" ")) {
                        fw.write(line.replaceAll(" ", ",") + "\r\n");
                    } else if (line.contains(";")) {
                        fw.write(line.replaceAll(";", ",") + "\r\n");
                    }
                }
                fw.close();
                br.close();
                fr.close();

                tempFile.renameTo(new File("C:\\Users\\Leah\\Desktop\\MachineLearning\\" + base_name + ".csv"));

                // load CSV
                CSVLoader loader = new CSVLoader();
                loader.setSource(new File("C:\\Users\\Leah\\Desktop\\MachineLearning\\" + base_name + ".csv"));
                Instances data = loader.getDataSet();

                // save ARFF
                ArffSaver saver = new ArffSaver();
                saver.setInstances(data);
                saver.setFile(new File("C:\\Users\\Leah\\Desktop\\MachineLearning\\" + base_name + ".arff"));
                //saver.setDestination(new File("C:\\Users\\Leah\\Desktop\\breast.arff"));
                saver.writeBatch();
            }
        }
    }

}

