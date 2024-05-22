package com.example.scsbro;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;



public class HelloController implements Initializable {



    @FXML
    private BorderPane borderPane;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textFieldEntry;

    @FXML
    private TextField textFieldEntryPort;

    @FXML
    private TreeView<String> treeViewFile;

    @FXML
    private HBox buttonBar;

    @FXML
    private ProgressBar progressBar;



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
    private void fastScan(ActionEvent event){
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

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
        }finally {
            progressBar.setProgress(1.0); // Configurar la barra de progreso como determinada (completa) al finalizar
        }
    }

    @FXML
    private void AdvancedScan( ActionEvent event){
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

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
        String nmapCommand = "nmap -sV -p "+ portNumber + " " + targetIp;


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
        }finally {
            progressBar.setProgress(1.0); // Configurar la barra de progreso como determinada (completa) al finalizar
        }
    }

    @FXML
    private void selectFile(){

        TreeItem<String> item = treeViewFile.getSelectionModel().getSelectedItem();

        if(item != null) {
            System.out.println(item.getValue());
        }
    }


    public void altMethod(ActionEvent event){
        System.out.println("testing Alt Method from keyboard");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //File Display Logic
        if (treeViewFile == null) {
            treeViewFile = new TreeView<>();
        }


        TreeItem<String> rootItem = new TreeItem<>("Files");
        rootItem.setExpanded(true);

        TreeItem<String> rootItemFastScan = new TreeItem<>("Fast Scan Files");
        rootItemFastScan.setExpanded(false);

        addFilesToTreeItem(rootItemFastScan, "fastScan");
        rootItem.getChildren().add(rootItemFastScan);

        TreeItem<String> rootItemAdvanceScan = new TreeItem<>("Advance Scan Files");
        rootItemAdvanceScan.setExpanded(false);
        addFilesToTreeItem(rootItemAdvanceScan, "advanceScan");
        rootItem.getChildren().add(rootItemAdvanceScan);

        treeViewFile.setRoot(rootItem);

        //Dark theme logic
        ToggleSwitch buttonTheme = new ToggleSwitch();

        SimpleBooleanProperty isOn = buttonTheme.switchOnProperty();
        isOn.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                buttonTheme.getScene().getRoot().getStylesheets().add(getClass().getResource("Style.css").toString());
                System.out.println("This is adding the dark mode");
            } else {
                buttonTheme.getScene().getRoot().getStylesheets().remove(getClass().getResource("Style.css").toString());
                System.out.println("This is removing the dark mode");
            }
        });


        if(buttonBar == null){
            buttonBar = new HBox();
        }
        buttonBar.getChildren().add(buttonTheme);

    }
    private void addFilesToTreeItem(TreeItem<String> parentItem, String directory) {
        File folder = new File(directory);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                TreeItem<String> item = new TreeItem<>(file.getName());
                parentItem.getChildren().add(item);
                if (file.isDirectory()) {
                    addSubItems(item, file.getPath());
                }
            }
        }
    }

    private void addSubItems(TreeItem<String> parentItem, String directory) {
        File folder = new File(directory);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                TreeItem<String> item = new TreeItem<>(file.getName());
                parentItem.getChildren().add(item);
                if (file.isDirectory()) {
                    addSubItems(item, file.getPath());
                }
            }
        }

//        TreeItem<File> rootItem = new TreeItem<>(new File("Files"));
//        rootItem.setExpanded(true);
//
//        TreeItem<File> rootItemFastScan = new TreeItem<>(new File("Fast Scan Files"));
//        rootItemFastScan.setExpanded(true);
//
//        File folderFastScan = new File("fastScan");
//        addFilesToTreeItem(rootItemFastScan, new File("fastScan"));
//        rootItem.getChildren().add(rootItemFastScan);
//
//
//        TreeItem<File> rootItemAdvanceScan = new TreeItem<>(new File("Advance Scan Files"));
//        rootItemAdvanceScan.setExpanded(true);
//        File folderAdvanceScan = new File("advanceScan");
//        addFilesToTreeItem(rootItemAdvanceScan, folderAdvanceScan);
//        rootItem.getChildren().add(rootItemAdvanceScan);
//
//
//        treeViewFile.setRoot(rootItem);
//
//        treeViewFile.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null && newValue.getValue().isFile()) {
//                displayFileContent(newValue.getValue());
//            }
//        });
    }

//    private void addFilesToTreeItem(TreeItem<File> parentItem, File directory) {
//        File[] files = directory.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                TreeItem<File> item = new TreeItem<>(file);
//                parentItem.getChildren().add(item);
//                if (file.isDirectory()) {
//                    addFilesToTreeItem(item, file);
//                }
//            }
//        }
//    }
//
//    private void addSubItems(TreeItem<File> parentItem, File directory) {
//        File[] files = directory.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                TreeItem<File> item = new TreeItem<>(file);
//                parentItem.getChildren().add(item);
//                if (file.isDirectory()) {
//                    addSubItems(item, file);
//                }
//            }
//        }
//    }
//
//    private void displayFileContent(File file) {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                content.append(line).append("\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (textArea != null) {
//            textArea.setText(content.toString());
//        } else {
//            System.err.println("Error: TextArea is null");
//        }
//    }
}