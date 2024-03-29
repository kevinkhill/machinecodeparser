package mcpGUI;

import java.awt.Color;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

public class mcpWin extends javax.swing.JFrame {
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mcpWin().setVisible(true);
            }
        });
    }    
    
    /** Creates new form mcpWin */    
    public mcpWin() {
        initComponents();
        setLocation(25,25);
        
        fis = null;
        bis = null;
        dis = null;

        program = null;
        inputFile = null;
        dirStructure = null;

        programNumberRegex = "O[0-9]{5}[ ]?";
        programNumberLineRegex = "O[0-9]{5}.*";
        inlineCommentRegex = "(O[0-9]{5}[ ]?)(\\(.*\\))(.*)";       
        programNumberWithCommentRegex = "O[0-9]{5}[ ]?\\(.*\\).*";
        
        if(debug) {
            f_inputFilePath.setText("R:\\My Desktop\\parseTest.txt");
            f_outputDirPath.setText("R:\\My Desktop\\testOutput");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dirChooserFrame = new javax.swing.JFrame();
        dirChooser = new javax.swing.JFileChooser();
        logFrame = new javax.swing.JFrame();
        container = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        log_m = new javax.swing.JMenuBar();
        log_m_file = new javax.swing.JMenu();
        log_m_file_close = new javax.swing.JMenuItem();
        log_m_file_clear = new javax.swing.JMenuItem();
        l_inputFile = new javax.swing.JLabel();
        f_inputFilePath = new javax.swing.JTextField();
        b_inputBrowse = new javax.swing.JButton();
        b_parse = new javax.swing.JButton();
        l_status = new javax.swing.JLabel();
        b_reset = new javax.swing.JButton();
        f_outputDirPath = new javax.swing.JTextField();
        b_outputBrowse = new javax.swing.JButton();
        l_outputDir = new javax.swing.JLabel();
        sep = new javax.swing.JSeparator();
        progressBar = new javax.swing.JProgressBar();
        main_m = new javax.swing.JMenuBar();
        main_m_file = new javax.swing.JMenu();
        main_m_file_close = new javax.swing.JMenuItem();
        main_m_view = new javax.swing.JMenu();
        main_m_view_log = new javax.swing.JMenuItem();

        javax.swing.GroupLayout dirChooserFrameLayout = new javax.swing.GroupLayout(dirChooserFrame.getContentPane());
        dirChooserFrame.getContentPane().setLayout(dirChooserFrameLayout);
        dirChooserFrameLayout.setHorizontalGroup(
            dirChooserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(dirChooserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dirChooserFrameLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(dirChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        dirChooserFrameLayout.setVerticalGroup(
            dirChooserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(dirChooserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dirChooserFrameLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(dirChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        logFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        logFrame.setTitle("Log Window");
        logFrame.setMinimumSize(new java.awt.Dimension(500, 300));
        logFrame.setName("logFrame"); // NOI18N

        log.setBackground(new java.awt.Color(0, 0, 0));
        log.setColumns(20);
        log.setEditable(false);
        log.setForeground(new java.awt.Color(0, 255, 51));
        log.setRows(5);
        container.setViewportView(log);

        log_m_file.setText("File");

        log_m_file_close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        log_m_file_close.setText("Close");
        log_m_file_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_m_file_closeActionPerformed(evt);
            }
        });
        log_m_file.add(log_m_file_close);

        log_m_file_clear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        log_m_file_clear.setText("Clear Log");
        log_m_file_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_m_file_clearActionPerformed(evt);
            }
        });
        log_m_file.add(log_m_file_clear);

        log_m.add(log_m_file);

        logFrame.setJMenuBar(log_m);

        javax.swing.GroupLayout logFrameLayout = new javax.swing.GroupLayout(logFrame.getContentPane());
        logFrame.getContentPane().setLayout(logFrameLayout);
        logFrameLayout.setHorizontalGroup(
            logFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        logFrameLayout.setVerticalGroup(
            logFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Machine Code Parsing Engine");
        setName("MCpe"); // NOI18N
        setResizable(false);

        l_inputFile.setText("Input File");

        b_inputBrowse.setText("Browse");
        b_inputBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_inputBrowseActionPerformed(evt);
            }
        });

        b_parse.setText("Parse File");
        b_parse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_parseActionPerformed(evt);
            }
        });

        l_status.setText("Please Select File");

        b_reset.setText("Reset");
        b_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_resetActionPerformed(evt);
            }
        });

        b_outputBrowse.setText("Browse");
        b_outputBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_outputBrowseActionPerformed(evt);
            }
        });

        l_outputDir.setText("Output Dir");

        main_m_file.setText("File");

        main_m_file_close.setText("Close");
        main_m_file_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_m_file_closeActionPerformed(evt);
            }
        });
        main_m_file.add(main_m_file_close);

        main_m.add(main_m_file);

        main_m_view.setText("View");

        main_m_view_log.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        main_m_view_log.setText("Log");
        main_m_view_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_m_view_logActionPerformed(evt);
            }
        });
        main_m_view.add(main_m_view_log);

        main_m.add(main_m_view);

        setJMenuBar(main_m);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_inputFile)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(f_inputFilePath, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_inputBrowse))
                    .addComponent(l_outputDir)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(f_outputDirPath, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_outputBrowse))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_parse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_reset))
                    .addComponent(sep, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_status)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_inputFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_inputFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_inputBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_outputDir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f_outputDirPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_outputBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_parse)
                    .addComponent(b_reset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(l_status)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_inputBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_inputBrowseActionPerformed
        dirChooser = new JFileChooser();
        dirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        if (evt.getSource() == b_inputBrowse) {
            int returnVal = dirChooser.showOpenDialog(dirChooserFrame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = dirChooser.getSelectedFile();
                f_inputFilePath.setText(file.getPath());
            }
        }
}//GEN-LAST:event_b_inputBrowseActionPerformed

    private void b_parseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_parseActionPerformed
        if (evt.getSource() == b_parse) {
            if (f_inputFilePath.getText().equals("")) {
                setStatus('e', "Error: Please Select Input File");
            } else if (f_outputDirPath.getText().equals("")) {
                setStatus('e', "Error: Please Select Output Directory");
            } else {
                inputFilePath = f_inputFilePath.getText();
                outputDirPath = f_outputDirPath.getText();

                progressBar.setMinimum(0);
                progressBar.setMaximum(count());
                new ParsingThread().start();

                setStatus('m', "Parsing ...");
            }
        }
    }//GEN-LAST:event_b_parseActionPerformed

    private void b_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_resetActionPerformed
        setStatus('m', "Idle");
        f_inputFilePath.setText("");
        f_outputDirPath.setText("");
    }//GEN-LAST:event_b_resetActionPerformed

    private void main_m_file_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_m_file_closeActionPerformed
        System.exit(0);
    }//GEN-LAST:event_main_m_file_closeActionPerformed

    private void b_outputBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_outputBrowseActionPerformed
        dirChooser = new JFileChooser();
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (evt.getSource() == b_outputBrowse) {
            int returnVal = dirChooser.showOpenDialog(dirChooserFrame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = dirChooser.getSelectedFile();
                f_outputDirPath.setText(file.getPath());
            }
        }
    }//GEN-LAST:event_b_outputBrowseActionPerformed

    private void log_m_file_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_m_file_closeActionPerformed
        logFrame.setVisible(false);
    }//GEN-LAST:event_log_m_file_closeActionPerformed

    private void main_m_view_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_m_view_logActionPerformed
        logFrame.setLocation(480,20);
        logFrame.setVisible(true);
    }//GEN-LAST:event_main_m_view_logActionPerformed

    private void log_m_file_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_m_file_clearActionPerformed
        log.setText("");
    }//GEN-LAST:event_log_m_file_clearActionPerformed

// ================== MY METHODS =================
    public static String cleanFilename(String fn) {
        String tmp = fn.replaceAll(fnCleanRegex, "");
        tmp = tmp.trim();
        return tmp.replace(" ", "_");
    }

    private void setProgress(int amnt) {
        progressBar.setValue(amnt);
    }
 
    private void setStatus(char t, String msg){
        switch(t) {
            case 'e':
                l_status.setForeground(Color.decode("#CC0000"));
            break;
            case 's':
                l_status.setForeground(Color.decode("#009933"));
            break;
            case 'm':
                l_status.setForeground(Color.black);
            break;            
        }
        l_status.setText(msg);
    }
    
    private void toLog(String msg){
        String tmp = log.getText();
        log.setText(msg  + "\n" + tmp);
    }
    
    private int count() {
        int count = 0;
        try {
            inputFile = new File(inputFilePath);
            fis = new FileInputStream(inputFile);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            String currentLine = "";

            while (dis.available() != 0) {
                currentLine = dis.readLine();
                
                if(currentLine.equals("") != true) {
                    if(currentLine.matches(programNumberLineRegex)) {
                        count++;
                    }
                }
            }
        } catch (IOException ex) {
            setStatus('e', "Error: Check Log Window");
            toLog(ex.getMessage());            
        }
        
        return count;
    }    
    
    class ParsingThread extends Thread {
        @Override
        public void run() {
            try {
                inputFile = new File(inputFilePath);
                try {
                    fis = new FileInputStream(inputFile);
                } catch (FileNotFoundException ex) {
                    setStatus('e', "Error: File Not Found");
                    toLog(ex.getMessage());
                }
                bis = new BufferedInputStream(fis);
                dis = new DataInputStream(bis);

                String currentLine = "";

                Boolean started = false;
                Boolean getTitle = false;
                currentCount = 0;

                while (dis.available() != 0) {
                    currentLine = dis.readLine();

                    if(currentLine.equals("") != true) {
                        if(currentLine.matches(programNumberLineRegex)) {
                           if(started == false) {
                                started = true;
                                            currentCount++;
                                setProgress(currentCount);

                                program = new cncProgram();
                            } else {
                                started = false;
                                program.addLine("%");
                                program.setOutputDir(outputDirPath);

                                String result = program.writeToFile();
                                toLog(result);

                                started = true;
                                            currentCount++;
                                setProgress(currentCount);

                                program = new cncProgram();
                            }

                            if(started == true) {
                                if(currentLine.matches(programNumberWithCommentRegex)) {
                                    program.addLine("%");
                                    program.addLine(currentLine);
                                    String tmp = currentLine.replaceFirst(inlineCommentRegex, "$2");
                                    program.setTitle(tmp);
                                }

                                if(currentLine.matches(programNumberRegex)){
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

                program.setOutputDir(outputDirPath);
                String result = program.writeToFile();
                toLog(result);

                program = null;
                fis.close();
                bis.close();
                dis.close();

                toLog("Parsing Complete");
                setStatus('s', "Parsing Complete");
            } catch (IOException ex) {
                setStatus('e', "Error: Check Log Window");
                toLog(ex.getMessage());
            }            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_inputBrowse;
    private javax.swing.JButton b_outputBrowse;
    private javax.swing.JButton b_parse;
    private javax.swing.JButton b_reset;
    private javax.swing.JScrollPane container;
    private javax.swing.JFileChooser dirChooser;
    private javax.swing.JFrame dirChooserFrame;
    private javax.swing.JTextField f_inputFilePath;
    private javax.swing.JTextField f_outputDirPath;
    private javax.swing.JLabel l_inputFile;
    private javax.swing.JLabel l_outputDir;
    private javax.swing.JLabel l_status;
    private javax.swing.JTextArea log;
    private javax.swing.JFrame logFrame;
    private javax.swing.JMenuBar log_m;
    private javax.swing.JMenu log_m_file;
    private javax.swing.JMenuItem log_m_file_clear;
    private javax.swing.JMenuItem log_m_file_close;
    private javax.swing.JMenuBar main_m;
    private javax.swing.JMenu main_m_file;
    private javax.swing.JMenuItem main_m_file_close;
    private javax.swing.JMenu main_m_view;
    private javax.swing.JMenuItem main_m_view_log;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JSeparator sep;
    // End of variables declaration//GEN-END:variables
 
//MY VARIABLES    
    private int currentCount;
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
    private static String fnCleanRegex = "[\\*|\\(|\\)|\\;|\\:|\\?|\\>|\\<|\\/|\\.]";

//DEBUGGING
    private Boolean debug = false;
//DEBUGGING
}