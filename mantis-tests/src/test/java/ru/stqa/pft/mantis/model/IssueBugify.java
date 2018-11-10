package ru.stqa.pft.mantis.model;

public class IssueBugify {
    private int id;
    private String subject;
    private String description;

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    private String state_name;

    public int getId() {
        return id;
    }

    public IssueBugify withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueBugify that = (IssueBugify) o;

        if (id != that.id) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return state_name != null ? state_name.equals(that.state_name) : that.state_name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (state_name != null ? state_name.hashCode() : 0);
        return result;
    }

    public String getState_name() {
        return state_name;
    }



    public String getSubject() {
        return subject;
    }

    public IssueBugify withSubject(String subject) {
        this.subject = subject;
        return this;

    }

    public String getDescription() {
        return description;
    }

    public IssueBugify withDescription(String description) {
        this.description = description;
        return this;

    }

}
