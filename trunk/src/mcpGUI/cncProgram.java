package mcpGUI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class cncProgram {

    private String programTitle = "";
    private String contents = "";
    private String outputDir = "";
    private Boolean odSet;

    public cncProgram() {
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

    public String writeToFile() throws IOException {
        if(odSet == true) {
            programTitle = mcpWin.cleanFilename(programTitle);

            try {
                String outputString = outputDir + "\\\\" + programTitle + ".txt";
                String lines[] = contents.split("\n");
                for(int a = 0; a < lines.length; a++){
                    writeLineToFile(outputString, lines[a], true);
                }
            } catch (IOException ex) {
                return ex.getMessage();
            }
            return "Successfully wrote \"" + programTitle + "\" to file.";
        } else {
            return "Output directory not set";
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
                pw = new PrintWriter(new FileWriter(filename, true));
            } else {
                pw = new PrintWriter(new FileWriter(filename));
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