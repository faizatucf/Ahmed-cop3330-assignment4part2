package ucf.assignments;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class list implements Serializable{
    private String task;
    private String date;
    private Boolean status;

    public list(String task, LocalDate date, boolean status)
    {
        this.task = task;
        this.status = status;
        this.date = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    //getters
    public String getTask()
    {

        return task;
    }

    public String getDate()
    {
        return date;
    }

    public boolean getstatus()
    {

        return status;
    }

    public ObservableBooleanValue isChecked() {
        return new SimpleBooleanProperty(status);
    }

    //setters
    public void setTask(String task)
    {

        this.task = task;
    }

    public void setDate(String date)
    {
        this.date = date;
    }


    public void setstatus(boolean status)
    {

        this.status =status;
    }

    public void changeStatus()
    {
        if(getstatus())
            setstatus(false);
        else
            setstatus(true);
    }

    public ObservableValue<Boolean> selectedProperty() {
        return new SimpleBooleanProperty(status);
    }


}
