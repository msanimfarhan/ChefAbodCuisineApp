package ca.dal.cs.csci3130.a2;

class User {
    public String accessWidgets() {
        return "I can access the widgets!";
    }
}

public class Dashboard extends User {
    public String accessWidgets() {
        return this.accessWidgets();
    }
}








