package mcpGUI;

import java.io.*;
import java.util.logging.*;
import java.util.regex.*;

public class parsingEngine {
    private cncProgram program;

    private String inputFilePath = "";;
    private String outputDirPath = "";;
    private File inputFile;

    private Boolean dirStructure;

    private FileInputStream fis;
    private BufferedInputStream bis;
    private DataInputStream dis;


    public void parsingEngine() {
        fis = null;
        bis = null;
        dis = null;

        inputFile = null;

        dirStructure = null;
        program = null;
    }

    public static String cleanFilename(String fn) {
        return fn.replaceAll("[\\(|\\)|\\;|\\:|\\?|\\>|\\<|\\/|\\.]", "").replace(" ", "_");
    }

    public void setInputFilePath(String str) {
        this.inputFilePath = str;
    }

    public String getInputFilePath() {
        return this.inputFilePath;
    }

    public void setOutputDirPath(String str) {
        this.outputDirPath = str;
    }

    public String getOutputDirPath() {
        return this.outputDirPath;
    }

    public void run() throws IOException {
        try {
            inputFile = new File(inputFilePath);
            fis = new FileInputStream(inputFile);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            String tmp = "";

            Boolean started = false;
            Boolean nameSet = false;
            int count = 0;

            while (dis.available() != 0) {
                tmp = dis.readLine();

                if(tmp.matches("%") == true) {
                    if(started == false) {
                        started = true;
                        count++;
                        program = new cncProgram();
                    } else {
                        nameSet = false;
                        started = false;
                        program.addLine(tmp);
                        program.setOutputDir(outputDirPath);
                        
                        Boolean t = program.writeFile();
                        misc.log(t.toString());
                        //misc.log(program.toString(count));
                    }

                    program.addLine(tmp);
                } else {
                    if(started == true && nameSet == false){
                        String check = tmp.substring(0, 1);

                        if(check.equals("(")) {
                            program.setTitle(tmp);
                            nameSet = true;
                        }
                    }

                    program.addLine(tmp);
                }
            }

            misc.log("\nDONE");

            fis.close();
            bis.close();
            dis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(parsingEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}