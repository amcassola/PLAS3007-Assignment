package edu.plas.testautoandci.ampc;

import java.util.Calendar;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 19/12/2015
 */
public class Note {
    private String title;
    private String body;
    private Calendar creationDate;

    public Note(){}
    public Note(String title, String body){
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar date) {
        this.creationDate = date;
    }
}
