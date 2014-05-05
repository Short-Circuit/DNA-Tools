package com.calebmilligan.dnatools;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class DNA_Tools {
    private String version = "1.2";
    private JTextPane textPane;
    private JFrame frmDnaTools;
    private JTextField textField;
    private JRadioButtonMenuItem rdbtnmntmRnadna;
    private JMenuBar menuBar;
    private JMenu mnTools;
    private JMenu mnTranslation;
    private JRadioButtonMenuItem rdbtnmntmFromRna;
    private JRadioButtonMenuItem rdbtnmntmIntoRna;
    private JRadioButtonMenuItem rdbtnmntmDnarna;
    private JMenu mnTranscription;
    private JScrollPane scrollPane;
    private JMenu mnHelp;
    private JTextPane helpPane;
    private JLabel lblStart;
    private Map<String, String> config;
    private String action = "none";
    private JMenuItem mntmProgramHelp;
    private JMenuItem mntmUpdate;
    private JTextPane updatePane;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DNA_Tools window = new DNA_Tools();
                    window.frmDnaTools.setVisible(true);
                }
                catch (Exception e) {
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public DNA_Tools() {
        initialize();
        try{
            String current = Download.checkUpdates();
            if(Double.parseDouble(version) < Double.parseDouble(current)){
                frmDnaTools.setTitle(frmDnaTools.getTitle() + " | A new update is available");
            }
        }
        catch(Exception e){

        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        Download.saveDefaults();
        frmDnaTools = new JFrame();
        frmDnaTools.setResizable(false);
        frmDnaTools.setTitle("DNA Tools v" + version + " by Caleb Milligan");
        frmDnaTools.setIconImage(Toolkit.getDefaultToolkit().getImage(DNA_Tools.class.getResource("/com/calebmilligan/dnatools/DNA.png")));
        frmDnaTools.setBounds(100, 100, 523, 300);
        frmDnaTools.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        frmDnaTools.setJMenuBar(menuBar);

        mnTools = new JMenu("Tools");
        mnTools.setMnemonic('T');
        menuBar.add(mnTools);

        mnTranslation = new JMenu("Translation");
        mnTranslation.setMnemonic('L');
        mnTools.add(mnTranslation);

        rdbtnmntmFromRna = new JRadioButtonMenuItem("From RNA");
        rdbtnmntmFromRna.setMnemonic('F');
        rdbtnmntmFromRna.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED){
                    action = "TranslateFrom";
                    textField.setEnabled(true);
                    textPane.setEnabled(true);
                    textField.setToolTipText("Translate from RNA");
                    helpPane.setVisible(false);
                    rdbtnmntmRnadna.setSelected(false);
                    rdbtnmntmDnarna.setSelected(false);
                    rdbtnmntmIntoRna.setSelected(false);
                    lblStart.setVisible(false);
                    updatePane.setVisible(false);
                    config = Config.configure();
                }
            }
        });
        mnTranslation.add(rdbtnmntmFromRna);

        rdbtnmntmIntoRna = new JRadioButtonMenuItem("Into RNA");
        rdbtnmntmIntoRna.setMnemonic('I');
        rdbtnmntmIntoRna.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED){
                    action = "TranslateInto";
                    textField.setEnabled(true);
                    textPane.setEnabled(true);
                    textField.setToolTipText("Translate into RNA");
                    helpPane.setVisible(false);
                    rdbtnmntmRnadna.setSelected(false);
                    rdbtnmntmDnarna.setSelected(false);
                    rdbtnmntmFromRna.setSelected(false);
                    lblStart.setVisible(false);
                    updatePane.setVisible(false);
                    config = Config.configure();
                }
            }
        });
        mnTranslation.add(rdbtnmntmIntoRna);

        mnTranscription = new JMenu("Transcription");
        mnTranscription.setMnemonic('C');
        mnTools.add(mnTranscription);

        rdbtnmntmRnadna = new JRadioButtonMenuItem("RNA->DNA");
        rdbtnmntmRnadna.setMnemonic('D');
        rdbtnmntmRnadna.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED){
                    action = "TranscribeDNA";
                    textField.setEnabled(true);
                    textPane.setEnabled(true);
                    textField.setToolTipText("Transcribe RNA into DNA");
                    helpPane.setVisible(false);
                    rdbtnmntmFromRna.setSelected(false);
                    rdbtnmntmIntoRna.setSelected(false);
                    rdbtnmntmDnarna.setSelected(false);
                    lblStart.setVisible(false);
                    updatePane.setVisible(false);
                    config = Config.configure();
                }
            }
        });
        mnTranscription.add(rdbtnmntmRnadna);

        rdbtnmntmDnarna = new JRadioButtonMenuItem("DNA->RNA");
        rdbtnmntmDnarna.setMnemonic('R');
        rdbtnmntmDnarna.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED){
                    action = "TranscribeRNA";
                    textField.setEnabled(true);
                    textPane.setEnabled(true);
                    textField.setToolTipText("Transcribe DNA into RNA");
                    helpPane.setVisible(false);
                    rdbtnmntmFromRna.setSelected(false);
                    rdbtnmntmIntoRna.setSelected(false);
                    rdbtnmntmRnadna.setSelected(false);
                    lblStart.setVisible(false);
                    updatePane.setVisible(false);
                    config = Config.configure();
                }
            }
        });
        mnTranscription.add(rdbtnmntmDnarna);

        mnHelp = new JMenu("Help");
        mnHelp.setMnemonic('H');
        menuBar.add(mnHelp);

        mntmProgramHelp = new JMenuItem("Program Help");
        mntmProgramHelp.setMnemonic('H');
        mntmProgramHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                helpPane.setVisible(true);
                rdbtnmntmFromRna.setSelected(false);
                rdbtnmntmIntoRna.setSelected(false);
                rdbtnmntmRnadna.setSelected(false);
                rdbtnmntmDnarna.setSelected(false);
                lblStart.setVisible(false);
                updatePane.setVisible(false);
            }
        });
        mnHelp.add(mntmProgramHelp);

        mntmUpdate = new JMenuItem("Check for Updates");
        mntmUpdate.setMnemonic('U');
        mntmUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                lblStart.setVisible(false);
                updatePane.setVisible(true);
                if(!lblStart.isVisible() && updatePane.isVisible()){
                    checkForUpdates();
                }
            }
        });
        mnHelp.add(mntmUpdate);
        frmDnaTools.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setEnabled(false);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER){
                    config = Config.configure();
                    if(config == null){
                        textPane.setForeground(new Color(255, 0, 0));
                        textPane.setText("Cannot load the configuration file.\n"
                                + "Does the file exist?");
                        textField.setEnabled(false);
                    }
                    if(!textField.getText().replaceAll(" ", "").equals("") && !textField.getText().equals(null)) {
                        String message = "";
                        switch(action){
                        case "TranslateFrom":
                            message = translate("from", textField.getText());
                            break;
                        case "TranslateInto":
                            message = translate("to", textField.getText());
                            break;
                        case "TranscribeRNA":
                            message = transcribe("rna", textField.getText());
                            break;
                        case "TranscribeDNA":
                            message = transcribe("dna", textField.getText());
                            break;
                        }
                        if(message.startsWith("Invalid")){
                            textPane.setForeground(new Color(255, 0, 0));
                        }
                        else{
                            textPane.setForeground(new Color(0, 0, 0));
                        }
                        textPane.setText(textPane.getText() + message + "\n");
                        textField.selectAll();
                        textPane.setCaretPosition(textPane.getDocument().getLength());
                    }
                }
            }
        });

        helpPane = new JTextPane();
        helpPane.setEditable(false);
        helpPane.setBounds(0, 0, 517, 251);
        helpPane.setHighlighter(null);
        helpPane.setText("To convert between RNA and DNA:\n"
                + "1. Click on \"Tools\"\n"
                + "2. Hover over \"Transcription\" to view the options\n"
                + "3. Choose to convert from RNA to DNA or vice versa\n"
                + "\n"
                + "To translate from or to RNA:\n"
                + "1. If you are starting with an DNA sequence, first convert to RNA\n"
                + "2. Click on \"Tools\"\n"
                + "3. Hover over \"Translation\" to view the options\n"
                + "4. Choose to translate from or to RNA\n"
                + "\n"
                + "To install new updates, click on \"Help\" and click \"Check for Updates\"\n"
                + "The updates are automatically downloaded, and the program will restart\n"
                + "You can configure the translation key by editing config.yml\n"
                + "NOTE: You must insert spaces between codons\n\n");
        helpPane.setVisible(false);

        lblStart = new JLabel("New label");
        lblStart.setIcon(new ImageIcon(DNA_Tools.class.getResource("/com/calebmilligan/dnatools/BG.gif")));
        lblStart.setBounds(0, 0, 517, 251);
        frmDnaTools.getContentPane().add(lblStart);

        updatePane = new JTextPane();
        updatePane.setEditable(false);
        updatePane.setHighlighter(null);
        updatePane.setBounds(0, 0, 517, 251);
        frmDnaTools.getContentPane().add(updatePane);
        frmDnaTools.getContentPane().add(helpPane);
        textField.setBounds(0, 0, 517, 20);
        frmDnaTools.getContentPane().add(textField);
        textField.setColumns(10);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 20, 517, 231);
        frmDnaTools.getContentPane().add(scrollPane);

        textPane = new JTextPane();
        textPane.setEnabled(false);
        scrollPane.setViewportView(textPane);
        textPane.setEditable(false);
    }
    public String transcribe(String convertTo, String original){
        original = original.replace(config.get("UAA"), " ");
        original = original.replace("! ", " ");
        original = original.replace("!", " ");
        original = original.replaceAll("\\? ", " ");
        original = original.replaceAll("\\?", " ");
        original = original.replace(". ", " ");
        original = original.replace(".", " ");
        original = original.replace(", ", " ");
        original = original.replace(",", " ");
        String[] sequences = original.toUpperCase().split(" ");
        String output = "";
        int sequencePos = 1;
        int basePos = 1;
        for(String sequence : sequences){
            char[] bases = sequence.toCharArray();
            if(bases.length != 3) {
                return "Invalid sequence: " + sequencePos;
            }
            basePos = 1;
            for(char base : bases){
                if(convertTo.equalsIgnoreCase("rna")){
                    if(base == 'T'){
                        output += "A";
                    }
                    else if(base == 'A'){
                        output += "U";
                    }
                    else if(base == 'G'){
                        output += "C";
                    }
                    else if(base == 'C'){
                        output += "G";
                    }
                    else{
                        return "Invalid base at sequence " + sequencePos + ": " + base;
                    }
                    if(basePos == 4){
                        return "Invalid sequence: " + sequencePos;
                    }
                }
                else if(convertTo.equalsIgnoreCase("dna")){
                    if(base == 'A'){
                        output += "T";
                    }
                    else if(base == 'U'){
                        output += "A";
                    }
                    else if(base == 'C'){
                        output += "G";
                    }
                    else if(base == 'G'){
                        output += "C";
                    }
                    else{
                        return "Invalid base at sequence " + sequencePos + ": " + base;
                    }
                    if(basePos == 4){
                        return "Invalid sequence: " + sequencePos;
                    }
                }
                basePos++;
            }
            output += " ";
            sequencePos++;
        }
        return output.toUpperCase();
    }
    public String translate(String convertTo, String original) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        original = original.replace(config.get("UAA"), " ");
        original = original.replace("! ", " ");
        original = original.replace("!", " ");
        original = original.replaceAll("\\? ", " ");
        original = original.replaceAll("\\?", " ");
        original = original.replace(". ", " ");
        original = original.replace(".", " ");
        original = original.replace(", ", " ");
        original = original.replace(",", " ");
        String output = "";
        if(convertTo.equals("from")) {
            String[] sequences = original.toUpperCase().split(" ");
            int sequencePos = 1;
            for(String sequence : sequences){
                String append = config.get(sequence);
                if(append == null) {
                    return "Invalid sequence at position " + sequencePos;
                }
                output += append;
                sequencePos++;
            }
        }
        else if(convertTo.equals("to")) {
            String[] stops = {"UGA", "UAG", "UAA"};
            Set<String> keys = config.keySet();
            Map<String, String> newMap = new HashMap<String, String>();
            for(String key : keys) {
                newMap.put(config.get(key), key);
            }
            String stop = stops[random.nextInt(3)];
            original = original.toUpperCase().replace(" ", config.get(stop));
            for(char check : original.toCharArray()) {
                String append = newMap.get(check + "");
                if(append == null) {
                    return "Invalid character: " + check;
                }
                output += append + " ";
            }
        }
        return output;
    }
    public boolean checkForUpdates(){
        updatePane.setVisible(true);
        lblStart.setVisible(false);
        String current = version;
        updatePane.setText("Connecting to server...\n");
        try{
            current = Download.checkUpdates();
        }
        catch(IOException e){
            updatePane.setForeground(new Color(255, 0, 0));
            updatePane.setText(updatePane.getText() + "Connection failed: " + e.getClass().getName() + "\n");
            return false;
        }
        updatePane.setText(updatePane.getText() + "Connected\n");
        if(Double.parseDouble(current) > Double.parseDouble(version)){
            updatePane.setText(updatePane.getText() + "New update found: v" + current + "\n");
            updatePane.setText(updatePane.getText() + "Downloading updates...\n");
            try{
                Download.download("https://dl.dropboxusercontent.com/s/hgmvrkspzeict6r/config.yml?dl=1&token_hash=AAFIdpetBrvnMvfmeKza-sVlzBW3MYyiiZCuWv0nAqe1Nw", "config.yml");
                Download.download("https://dl.dropboxusercontent.com/s/1uvu9xeg7tkeyz4/DNA%20Tools.jar?dl=1&token_hash=AAHiqLqki1fcNcA-zKo9LOLHeENPsvIyjtkI-nvJf5Rb1Q", "DNA Tools.jar");
                Download.download("https://dl.dropboxusercontent.com/s/uya42anxfinjauk/run.bat?dl=1&token_hash=AAGJXZOy-9xQjQDdB_B6n3HZajZq4p1J9uhsSWNUlMTM2w", "run.bat");
            }
            catch(Exception e){
                updatePane.setForeground(new Color(255, 0, 0));
                updatePane.setText(updatePane.getText() + "Download failed: " + e.getClass().getName());
                return false;
            }
            updatePane.setText(updatePane.getText() + "Download success\n");
            try{
                updatePane.setText(updatePane.getText() + "Restarting DNA Tools...\n");
                frmDnaTools.dispose();
                Runtime.getRuntime().exec("cmd /c start javaw -jar \"DNA Tools.jar\"");
            }
            catch(Exception e){
            }
            return true;
        }
        else{
            if(updatePane != null){
                updatePane.setText(updatePane.getText() + "No new updates found\n");
            }
            return false;
        }
    }
}
