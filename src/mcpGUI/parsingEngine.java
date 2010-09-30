package mcpGUI;

import java.io.*;
import java.util.logging.*;

public class parsingEngine {
    private FileInputStream fis;
    private BufferedInputStream bis;
    private DataInputStream dis;

    private String inputFilePath = "";
    private String outputDirPath = "";

    private cncProgram program;
    private File inputFile;
    private Boolean dirStructure;


    private String programNumberRegex;
    private String programNumberLineRegex;
    private String programNumberWithCommentRegex;
    private String inlineCommentRegex;
    private static String fnCleanRegex = "[\\(|\\)|\\;|\\:|\\?|\\>|\\<|\\/|\\.]";;

    public parsingEngine() {
        fis = null;
        bis = null;
        dis = null;

        program = null;
        inputFile = null;
        dirStructure = null;

        programNumberRegex = "O[0-9]{5};";
        programNumberLineRegex = "O[0-9]{5}.*";
        inlineCommentRegex = "(O[0-9]{5}[ ]*)(\\(.*\\))(;)";
        programNumberWithCommentRegex = "O[0-9]{5}[ ]*\\(.*\\);";
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
            Boolean getTitle = false;
            int count = 0;

            while (dis.available() != 0) {
                currentLine = dis.readLine();
                
                if(currentLine.equals("") != true) {
                    if(currentLine.matches(programNumberLineRegex)) {
                       if(started == false) {
                            started = true;
                            count++;
                            program = new cncProgram();
                        } else {
                            started = false;
                            program.addLine("%");
                            program.setOutputDir(outputDirPath);

                            String result = program.writeToFile();
                            misc.log(result);

                            count++;
                            program = new cncProgram();
                            program.addLine("%");
                            program.addLine(currentLine);
                        }

                        if(started == true) {
                            if(currentLine.matches(programNumberWithCommentRegex)) {
                                program.addLine("%");
                                program.addLine(currentLine);
                                String tmp = currentLine.replaceFirst(inlineCommentRegex, "$2");
                                program.setTitle(tmp);
                            }
                            
                            if(currentLine.matches("O[0-9]{5};")){
                                program.addLine("%");
                                program.addLine(currentLine);
                                getTitle = true;
                            }
                        }
                    } else {
                        if(started == true) {
                            if(getTitle == true) {
                                program.setTitle(currentLine);
                                getTitle = false;
                            }

                            program.addLine(currentLine);
                        }
                    }
                }
            }

            program.addLine("%");
            program.setOutputDir(outputDirPath);

            String result = program.writeToFile();
            misc.log(result);

            misc.log("Processed " + count + " programs");

            fis.close();
            bis.close();
            dis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(parsingEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}