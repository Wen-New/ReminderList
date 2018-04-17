package sg.edu.rp.c346.eseproject;


import android.content.Intent;
import android.os.Bundle;

public class Reminder {
    private int position;
    private String title;
    private String purpose;
    private String deadline;
    private String time;

    public Reminder(String title, String purpose, String deadline, String time) {
        this.title = title;
        this.purpose = purpose;
        this.deadline = deadline;
        this.time = time;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
