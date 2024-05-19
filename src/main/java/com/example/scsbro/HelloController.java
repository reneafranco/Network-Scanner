package com.example.scsbro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloController {



    @FXML
    private BorderPane borderPane;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textFieldEntry;

    @FXML
    private TextField textFieldEntryPort;

    @FXML
    private Label fastScanLabel;

    @FXML
    private Button fastScanButton;


    @FXML
    private void fastSceneButtonAction(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FastScene-View.fxml"));
            AnchorPane fastScenceContent = loader.load();

            borderPane.setCenter(fastScenceContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void advanceSceneButtonAction(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdvanceScene-View.fxml"));
            AnchorPane advanceScenceContent = loader.load();

            borderPane.setCenter(advanceScenceContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void SearchSceneButtonAction(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchScene-View.fxml"));
            AnchorPane searchScenceContent = loader.load();

            borderPane.setCenter(searchScenceContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void tutorialSceneButtonAction(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TutorialScene-View.fxml"));
            AnchorPane TutorialScenceContent = loader.load();

            borderPane.setCenter(TutorialScenceContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void mostratTextoTextArea(ActionEvent event){

        String[] lines = { "Primera línea\nSegunda línea\nTercera línea\nCuarta línea",
                "Primera línea\nSegunda línea\nTercera línea\nCuarta línea", "Primera línea\nSegunda línea\nTercera línea\nCuarta línea",
                "Primera línea\nSegunda línea\nTercera línea\nCuarta línea", "Primera línea\nSegunda línea\nTercera línea\nCuarta línea"};



        for (String line : lines) {
            textArea.appendText(line + "\n");
        }
    }

    @FXML
    public void fastScan(ActionEvent event){

        String targetIp = textFieldEntry.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());

        StringBuilder fileName = new StringBuilder();
        fileName.append("fastScan/")
                .append(timestamp)
                .append("_")
                .append(targetIp)
                .append("_nmap_output_FAST.txt");
        String nmapCommand = "nmap -F " + targetIp;

        try {
            Process process = Runtime.getRuntime().exec(nmapCommand);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            File outputFile = new File(fileName.toString());
            FileWriter writer = new FileWriter(outputFile);

            String line;

            while((line = reader.readLine()) != null){
                System.out.println(line);
                textArea.appendText(line + "\n");
                writer.write(line + "\n");
            }

            writer.close();
            process.waitFor();

            System.out.println("Nmap fast scan complete...");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void AdvancedScan( ActionEvent event){

        String targetIp = textFieldEntry.getText();

        int portNumber = Integer.parseInt(textFieldEntryPort.getText());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());

        StringBuilder fileName = new StringBuilder();
        fileName.append("advanceScan/")
                .append(timestamp)
                .append("_")
                .append(targetIp)
                .append("_nmap_output_AS.txt");
        String nmapCommand = "nmap -A -p "+ portNumber + " " + targetIp;


        try{
            Process process = Runtime.getRuntime().exec(nmapCommand);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            File outputFile = new File(fileName.toString());
            FileWriter writer = new FileWriter(outputFile);

            String line;

            while((line = reader.readLine()) != null){
                System.out.println(line);
                textArea.appendText(line + "\n");
                writer.write(line + "\n");
            }

            writer.close();
            process.waitFor();

            System.out.println("Nmap scan complete...");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void altMethod(ActionEvent event){
        System.out.println("testing Alt Method from keyboard");
    }


}