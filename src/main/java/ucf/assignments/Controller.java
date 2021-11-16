package ucf.assignments;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TableView<ucf.assignments.list> tableView;
    @FXML private TableColumn<ucf.assignments.list, Boolean> completedColumn;
    @FXML private TableColumn<ucf.assignments.list, String> dateColumn;
    @FXML private TableColumn<ucf.assignments.list, String> taskColumn;
    @FXML private TextField taskTextField;
    @FXML private DatePicker taskDatePicker;
    private ArrayList<ucf.assignments.list> all_items;
    private boolean filter;
    //@FXML private CheckBox ch;



    public Controller() {
        this.filter = false;
        this.all_items = new ArrayList<ucf.assignments.list>();
    }

    public void filter_show_error()
    {
        taskDatePicker.getEditor().clear();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Filter");
        alert.setContentText("Please disable filters by clicking the Clear filters button before proceeding.");
        alert.showAndWait();
    }

    public void Add_task_button()
    {
        if(this.filter)
        {
            filter_show_error();
            return;
        }
        if(!ucf.assignments.helper.check_task_length(taskTextField.getText()))
        {
            taskTextField.clear();
            taskDatePicker.getEditor().clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Description");
            alert.setContentText("A description shall be between 1 and 256 characters in length");
            alert.showAndWait();
            tableView.refresh();
        }
        else
        {
            ucf.assignments.list newList = new ucf.assignments.list(taskTextField.getText(),
                    taskDatePicker.getValue(),
                    false);
            tableView.getItems().add(newList);
            taskTextField.clear();
            taskDatePicker.getEditor().clear();
        }


    }

    public void check_button()
    {
        if(this.filter)
        {
            filter_show_error();
            return;
        }
        if(tableView.getSelectionModel().getSelectedItem() !=null)
        {
            ucf.assignments.list selectedList = tableView.getSelectionModel().getSelectedItem();
            if(selectedList.getstatus())
                selectedList.setstatus(false);
            else
                selectedList.setstatus(true);
            tableView.refresh();
        }
    }

    public void delete_button()
    {
        if(this.filter)
        {
            filter_show_error();
            return;
        }
        if(tableView.getSelectionModel().getSelectedItem() !=null)
        {
            ucf.assignments.list selectedList = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().removeAll(selectedList);
            tableView.refresh();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        tableView.setEditable(true);
        //CheckBox ch = new CheckBox(null);
        completedColumn.setCellValueFactory(new PropertyValueFactory<ucf.assignments.list,Boolean>("status"));
        //completedColumn.setCellFactory(column -> new CheckBoxTableCell());
        completedColumn.setCellValueFactory(
                param -> param.getValue().isChecked()
        );
        completedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(completedColumn));

        dateColumn.setCellValueFactory(new PropertyValueFactory<ucf.assignments.list,String>("date"));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        dateColumn.setOnEditCommit(new EventHandler<CellEditEvent<ucf.assignments.list, String>>() {
            @Override
            public void handle(CellEditEvent<ucf.assignments.list, String> event) {
                if(!ucf.assignments.helper.check_date_format(event.getNewValue()))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Date");
                    alert.setContentText("A due date should be in the format yyyy-MM-dd");
                    alert.showAndWait();
                    tableView.refresh();
                }
                else{
                    ucf.assignments.list newList = event.getRowValue();
                    newList.setDate(event.getNewValue());
                }

            }
        });

        //make editor but also checker and convertor for date
        taskColumn.setCellValueFactory(new PropertyValueFactory<ucf.assignments.list,String>("task"));
        taskColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        taskColumn.setOnEditCommit(new EventHandler<CellEditEvent<ucf.assignments.list, String>>() {
            @Override
            public void handle(CellEditEvent<ucf.assignments.list, String> event) {

                if(!ucf.assignments.helper.check_task_length(event.getNewValue()))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Description");
                    alert.setContentText("A description shall be between 1 and 256 characters in length");
                    alert.showAndWait();
                    tableView.refresh();
                }
                else{
                    ucf.assignments.list newList = event.getRowValue();
                    newList.setTask(event.getNewValue());
                }

            }
        });

    }

    public void export(String filename)
    {
        if(tableView.getItems().size()>0 && filename!=null) {
            ArrayList<ucf.assignments.list> datalist = new ArrayList<>();
            for (int i = 0; i < tableView.getItems().size(); i++) {
                ucf.assignments.list selectedList = tableView.getItems().get(i);
                datalist.add(selectedList);
            }

            try {
                FileOutputStream fos = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(datalist);
                oos.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


    public void resume(String filename)
    {
        ArrayList<ucf.assignments.list> datalist = new ArrayList<>();
        if(filename!=null) {
            try {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                datalist = (ArrayList<ucf.assignments.list>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        if(datalist!=null)
        {
            tableView.getItems().clear();
            for(int i=0; i< datalist.size(); i++)
            {
                ucf.assignments.list newlist = datalist.get(i);
                tableView.getItems().add(newlist);
            }
        }
    }

    public void load()
    {
        if(this.filter)
        {
            filter_show_error();
            return;
        }
        FileChooser fc = new FileChooser();
        fc.setTitle("Open file");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Dat files","*.dat"));
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile !=null)
        {
            resume(selectedFile.getAbsolutePath());
        }
    }

    public void menu_save()
    {
        if(this.filter)
        {
            filter_show_error();
            return;
        }

        FileChooser fc = new FileChooser();
        fc.setTitle("Save");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Dat files","*.dat"));
        File selectedFile = fc.showSaveDialog(null);
        if(selectedFile !=null)
        {
            export(selectedFile.getAbsolutePath());
        }
    }

    public void Show_only_completed()
    {
        //save main list to global list.ff
        if(!this.filter)
        {
            if(tableView.getItems().size()>0) {
                for (int i = 0; i < tableView.getItems().size(); i++) {
                    ucf.assignments.list selectedList = tableView.getItems().get(i);
                    this.all_items.add(selectedList);
                }
            }
        }
        else
        {
            resettable();
        }
        this.filter = true;
        ArrayList<ucf.assignments.list> tmplist = new ArrayList<ucf.assignments.list>();

        for(int i = 0; i<tableView.getItems().size();i++)
        {
            ucf.assignments.list selectedList = tableView.getItems().get(i);
            if(selectedList.getstatus())
            tmplist.add(selectedList);
        }
        tableView.getItems().removeAll(tmplist);
    }

    public void hide_completed()
    {
        //save main list to global list.
        if(!this.filter)
        {
            if(tableView.getItems().size()>0) {
                for (int i = 0; i < tableView.getItems().size(); i++) {
                    ucf.assignments.list selectedList = tableView.getItems().get(i);
                    this.all_items.add(selectedList);
                }
            }
        }
        else
        {
            resettable();
        }
        this.filter = true;
        ArrayList<ucf.assignments.list> tmplist = new ArrayList<ucf.assignments.list>();

        for(int i = 0; i<tableView.getItems().size();i++)
        {
            ucf.assignments.list selectedList = tableView.getItems().get(i);
            if(!selectedList.getstatus())
                tmplist.add(selectedList);
        }
        tableView.getItems().removeAll(tmplist);
    }

    public void show_all()
    {
        if(this.filter)
        {
            resettable();
            this.all_items.clear();
            this.filter = false;
            tableView.refresh();
        }

        //System.out.println(this.all_items);
    }

    public void resettable()
    {
        tableView.getItems().clear();
        for(int i=0; i< this.all_items.size(); i++)
        {
            ucf.assignments.list newlist = this.all_items.get(i);
            tableView.getItems().add(newlist);
        }
    }

    public void clear_all_items()
    {
        if(this.filter)
        {
            this.all_items.clear();
            this.filter = false;
        }
        tableView.getItems().clear();
        tableView.refresh();
    }

    public void open_readme() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/faizatucf/Ahmed-cop3330-assignment4part2/blob/main/README.md"));
    }
}
