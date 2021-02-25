package controllerview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Person;
import model.TelephonelistM;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Oliver Steiner
 * @version: 1, 24.02.2021
 */

public class TelephonelistC implements Initializable {



    @FXML
    private TextArea txtName;
    @FXML
    private TextArea txtAddress;
    @FXML
    private TextArea txtPhoneNum;
    @FXML
    private Label error;
    @FXML
    private Label couter;

    TelephonelistM telM = new TelephonelistM();
    int index;

    //create stage
    public static void show(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TelephonelistC.class.getResource("/controllerview/view.fxml"));
            Parent root = fxmlLoader.load();

            stage.setTitle("Telephone-List");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e){
            System.err.println("Something wrong with view.fxml: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    @FXML
    public void addNewPerson(){
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phoneNum = txtPhoneNum.getText();

        Person p = new Person(name, address, phoneNum);
        try{
            if(txtName.getText().equals("") && txtAddress.getText().equals("") && txtPhoneNum.getText().equals("")){
                error.setText("Check your Intput");
            }
            else{
                telM.personList.add(p);
                error.setText("Person was successfully added!");
            }
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
            error.setText("Something went wrong!");
        }
    }

    @FXML
    public void removePerson(){
        try{
            telM.personList.remove(index);
            txtName.setText("");
            txtAddress.setText("");
            txtPhoneNum.setText("");
            error.setText("Person was successfully deleted");
        }
        catch (Exception ex){
            error.setText("Something went wrong!");
        }
    }

    @FXML
    public void changePerson(){
        Person p = telM.personList.get(index);

        String name = p.getName();
        String address = p.getAddress();
        String phoneNum = p.getPhoneNr();
        String changedName = txtName.getText();
        String changedAddress = txtAddress.getText();
        String changedPhoneNum = txtPhoneNum.getText();
        if(name.equals(changedName) && address.equals(changedAddress) && phoneNum.equals(changedPhoneNum)){
            error.setText("There are no changes!");
        }
        else{
            p.setName(changedName);
            p.setAddress(changedAddress);
            p.setPhoneNum(changedPhoneNum);

            System.out.println("Peron was successfully changed!");
        }
    }

    @FXML
    public void next(){
        if(telM.personList.size() >= 1){
            if (index < telM.personList.size() - 1) {
                index++;
            }
            else {
                index = 0;
            }
            print(index);
        }
    }

    @FXML
    public  void previous(){
        if(telM.personList.size() >= 1){
            if(index > 0){
                index --;
            }
            else{
                index = telM.personList.size() -1;
            }
            print(index);
        }
    }

    @FXML
    public boolean saveToFile(){

            boolean saved = false;
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("TelephoneList.csv")))
            {
                for (int i = 0; i < telM.personList.size(); i++) {
                    bufferedWriter.write(String.valueOf(telM.personList.get(i).getName()));
                    bufferedWriter.write(",");
                    bufferedWriter.write(String.valueOf(telM.personList.get(i).getAddress()));
                    bufferedWriter.write(",");
                    bufferedWriter.write(telM.personList.get(i).getPhoneNr());
                    bufferedWriter.newLine();
                    saved = true;
                    error.setText("TelephoneList was successfully saved to file");
                }
            }
            catch (IOException ex)
            {
                System.out.println("Something went wrong");
            }

            return saved;
    }

    @FXML
    public boolean loadFromFile(){
        boolean saved = false;
        telM.personList.clear();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("TelephoneList.csv")))
        {
            String line;
            while((line = bufferedReader.readLine()) != null){
                String rows[] = line.split(",");
                String rowName = rows[0];
                String rowAddress = rows[1];
                String rowPhoneNr = rows[2];

                Person row123 = new Person(rowName, rowAddress, rowPhoneNr);

                telM.personList.add(row123);
            }
            print(0);
        }
        catch (IOException exception)
        {
            System.out.println("Something went wrong");
        }

        return saved;
    }

    public void print(int index){
        txtName.setText(telM.personList.get(index).getName());
        txtAddress.setText(telM.personList.get(index).getAddress());
        txtPhoneNum.setText(telM.personList.get(index).getPhoneNr());

        couter.setText(index + 1 + "/" + telM.personList.size());
    }

    @FXML
    public void clear(){
        txtName.setText(null);
        txtAddress.setText(null);
        txtPhoneNum.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Person p = new Person("Oliver", "Lindenstr. 7b", "1234");
        Person p1 = new Person("Thomas", "Lindenstr. 7a", "5678");
        telM.personList.add(p);
        telM.personList.add(p1);
        print(0);
    }

}
