package mcpGUI;

import java.io.*;
import java.util.HashMap;
import java.util.logging.*;

public class parsingEngine {
    private cncProgram program;

    private String inputFilePath = "";
    private String outputDirPath = "";
    private File inputFile;

    private Boolean dirStructure;

    private FileInputStream fis;
    private BufferedInputStream bis;
    private DataInputStream dis;

    private static String fnCleanRegex = "[\\(|\\)|\\;|\\:|\\?|\\>|\\<|\\/|\\.]";

    private String programNumber = "O[0-9]{5}[ ]*;";
    private String programNumberWithComment = "O[0-9]{5}[ ]*\\(.*\\);";
    private String comment = "\\(.*\\);";
    private String inlineComment = "(O[0-9]{5}[ ]*)(\\(.*\\))(;)";

    public void parsingEngine() {
        fis = null;
        bis = null;
        dis = null;

        inputFile = null;

        dirStructure = null;
        program = null;
    }

    public static String cleanFilename(String fn) {
        String tmp = fn.replaceAll(fnCleanRegex, "");
        tmp = tmp.trim();
        return tmp.replace(" ", "_");
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

            String currentLine = "";

            Boolean started = false;
            Boolean nameSet = false;
            String workingProg = "";
            int count = 0;

            while (dis.available() != 0) {
                currentLine = dis.readLine();

                if(currentLine.matches(programNumber) || currentLine.matches(programNumberWithComment)) {
                    if(currentLine.matches(programNumber)) {
                        if(!started) {
                            started = true;
                            count++;
                            program = new cncProgram();
                        } else {
                            nameSet = false;
                            started = false;
                            program.addLine(currentLine);
                            program.addLine("%");
                            program.setOutputDir(outputDirPath);

                            String result = program.writeToFile();
                            misc.log(result);
                        }

                        program.addLine("%");
                        program.addLine(currentLine);
                    }

                    if(currentLine.matches(programNumberWithComment)){
                        program.setTitle(currentLine.replaceFirst(inlineComment, "$2"));
                        nameSet = true;
                    }

                    program.addLine("%");
                    program.addLine(currentLine);
                } else {
                    if(started == true && nameSet == false){
                        if(currentLine.matches(comment)) {
                            program.setTitle(currentLine);
                            nameSet = true;
                        }
                    }

                    program.addLine(currentLine);
                }
            }

            misc.log("Processed " + count + " programs");

            fis.close();
            bis.close();
            dis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(parsingEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}