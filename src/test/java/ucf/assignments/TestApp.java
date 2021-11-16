/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Faiz Ahmed
 */

package ucf.assignments;
import static org.junit.jupiter.api.Assertions.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;

//Testing functionalities of Gui and functions... From #6 to #20 in assignment requirements.
class TestApp {


    @Test
    @DisplayName("Test if user is be able to add a new todo list")
    public void addnewtask() {
        //create new list object and add new task to it.
        //Add same task to tableview using Add_task_button...
        //Check assertion equals to the same list returned by tableview as the list object made...

        //ObservableList represents list in table view.
        ObservableList<list> Tblist = FXCollections.observableArrayList();


        ArrayList<list> listTest = new ArrayList<>();


        list newList = new list("This is a test task",helper.getLocalDate("2001/03/12"), false);
        list newList1 = new list("This is a test task 1",helper.getLocalDate("2021/03/22"), false);
        list newList2 = new list("This is a test task 2",helper.getLocalDate("2011/03/13"), false);
        listTest.add(newList);
        listTest.add(newList1);
        listTest.add(newList2);

        Tblist.add(newList);
        Tblist.add(newList1);
        Tblist.add(newList2);

        assertArrayEquals(Tblist.toArray(), listTest.toArray());

    }

    @Test
    @DisplayName("Test if the user can mark items as complete or Incomplete successfully!")
    public void CheckmarkAsCompleteTest() {
        //test Item

        String task = "This is a test task";
        String date = "2001/03/12";
        ucf.assignments.list newList = new ucf.assignments.list(task,
                helper.getLocalDate(date),
                false);

        //setting check box as marked...
        newList.changeStatus();

        assertTrue(newList.getstatus());
    }

    @Test
    @DisplayName("Test if the user can mark items as Incomplete successfully!")
    public void CheckmarkAsCompleteTest2() {
        String task = "This is a test task";
        String date = "2001/03/12";
        ucf.assignments.list newList = new ucf.assignments.list(task,
                helper.getLocalDate(date),
                false);

        //setting check box as marked...
        newList.changeStatus();

        //setting check box as unmarked
        newList.changeStatus();

        assertFalse(newList.getstatus());
    }

    @Test
    @DisplayName("Test if the user shall be able to remove an existing todo list")
    public void deleteItem() {
        //Make new list object
        //Launch new instance of application
        //Add a new todo task
        //delete the task
        //check by comparing if the task has been deleted

        //create new list object and add new task to it.
        //Add same task to tableview using Add_task_button...
        //Check assertion equals to the same list returned by tableview as the list object made...

        //ObservableList represents list in table view.
        ObservableList<list> Tblist = FXCollections.observableArrayList();


        ArrayList<list> listTest = new ArrayList<>();


        list newList = new list("This is a test task",helper.getLocalDate("2001/03/12"), false);
        list newList1 = new list("This is a test task 1",helper.getLocalDate("2021/03/22"), false);
        list newList2 = new list("This is a test task 2",helper.getLocalDate("2011/03/13"), false);
        listTest.add(newList);
        listTest.add(newList1);
        listTest.add(newList2);

        Tblist.add(newList);
        Tblist.add(newList1);
        Tblist.add(newList2);

        Tblist.remove(newList2);

        listTest.remove(newList2);
        assertArrayEquals(Tblist.toArray(), listTest.toArray());

    }

    @Test
    @DisplayName("Test if the user shall be able to remove two existing todo lists")
    public void deleteItem2() {
        //Make new list object
        //Launch new instance of application
        //Add a new todo task
        //delete the task
        //check by comparing if the task has been deleted

        //create new list object and add new task to it.
        //Add same task to tableview using Add_task_button...
        //Check assertion equals to the same list returned by tableview as the list object made...

        //ObservableList represents list in table view.
        ObservableList<list> Tblist = FXCollections.observableArrayList();


        ArrayList<list> listTest = new ArrayList<>();


        list newList = new list("This is a test task",helper.getLocalDate("2001/03/12"), false);
        list newList1 = new list("This is a test task 1",helper.getLocalDate("2021/03/22"), false);
        list newList2 = new list("This is a test task 2",helper.getLocalDate("2011/03/13"), false);
        listTest.add(newList);
        listTest.add(newList1);
        listTest.add(newList2);

        Tblist.add(newList);
        Tblist.add(newList1);
        Tblist.add(newList2);

        Tblist.remove(newList2);
        Tblist.remove(newList1);

        listTest.remove(newList2);
        listTest.remove(newList1);

        assertArrayEquals(Tblist.toArray(), listTest.toArray());

    }

    @Test
    @DisplayName("Test if the user shall be able to display only the completed items in a todo list.")
    public void Show_completed_Elements() {
        //Make new list object
        //Launch new instance of application
        //Add few new todo tasks
        //Mark some of them completed
        //Call Show_only_completed()
        //Check by comparing the tableView list to see if all items are marked completed.


    }

    @Test
    @DisplayName("Test if items selected task and date can be edited...")
    public void editItem() {
        //Make new list object
        //Launch new instance of application
        //Add new todo tasks
        //Edit task for one of items
        //Compare to check if that item task is different than the one originally added...
        //Edit date for one of items
        //Compare to check if that item date is different than the one originally added...

        //ObservableList represents list in table view.
        ObservableList<list> Tblist = FXCollections.observableArrayList();


        ArrayList<list> listTest = new ArrayList<>();


        list newList = new list("This is a test task",helper.getLocalDate("2001/03/12"), false);
        list newList1 = new list("This is a test task 1",helper.getLocalDate("2021/03/22"), false);
        list newList2 = new list("This is a test task 2",helper.getLocalDate("2011/03/13"), false);
        listTest.add(newList);
        listTest.add(newList1);

        Tblist.add(newList);
        Tblist.add(newList1);

        //editing values...

        Tblist.add(0, newList2);
        listTest.add(0, newList2);

        assertArrayEquals(Tblist.toArray(), listTest.toArray());

    }

    @Test
    @DisplayName("Check if date is being formatted correctly...")
    public void formatDate(){
        //declare a date string with the format YYYY-mm-dd...
        //Assert true if check_date_format() is true

        //declare a date string with wrong format
        //Assert false if check_date_format() is true

        String task = "This is a test task";
        String date = "2001/03/12";
        ucf.assignments.list newList = new ucf.assignments.list(task,
                helper.getLocalDate(date),
                false);

        //setting check box as marked...
        newList.changeStatus();

        assertTrue(helper.check_date_format(date));

    }

}