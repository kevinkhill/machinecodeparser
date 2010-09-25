package mcpGUI;

import java.io.*;
import java.util.logging.*;

public class cncProgram {

    private String programTitle = "";
    private String contents = "";
    private String outputDir = "";
    private Boolean odSet;

    public void cncProgram() {
        odSet = false;
    }

    public void setTitle(String str) {
        programTitle = str;
    }

    public String getTitle() {
        return programTitle;
    }

    public void addLine(String str) {
        contents += str + "\n";
    }

    public void setOutputDir(String dir){
        outputDir = dir;
        odSet = true;
    }

    public Boolean writeFile() throws IOException {
        if(odSet == true) {
            programTitle = parsingEngine.cleanFilename(programTitle);

            try {
                String outputString = outputDir + "\\\\" + programTitle + ".txt";
                String lines[] = contents.split("\n");
                for(int a = 0; a < lines.length; a++){
                    writeLineToFile(outputString, lines[a], true);
                }
            } catch (IOException ex) {
                Logger.getLogger(parsingEngine.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public String toString(int a){
        return a + "  Title: " + programTitle + "\n\n" + contents;
    }

    public void writeLineToFile(String filename,
                                 String lineToWrite,
                                 boolean appendToFile) throws IOException {

        PrintWriter pw = null;
        try {
            if (appendToFile) {
                //If the file already exists, start writing at the end of it.
                pw = new PrintWriter(new FileWriter(filename, true));
            } else {
                pw = new PrintWriter(new FileWriter(filename));
                //this is equal to:
                //pw = new PrintWriter(new FileWriter(filename, false));
            }

        pw.println(lineToWrite);
        pw.flush();

        } catch (IOException e) {
            throw e;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
