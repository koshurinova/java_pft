package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UserDate {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "username")
    public String username;
    @Expose
    @Column(name = "email")
    public String email;

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public UserDate withId(int id) {
        this.id = id;
        return this;
    }

    public UserDate withUsername(String username) {
        this.username = username;
        return this; //меняем возвращаемое значение. Метод будет возвращать объект, в котором он вызван
    }

    public UserDate withEmail(String email) {
        this.email = email;
        return this;
    }


    @Override
    public String toString() {
        return "UserDate{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDate userDate = (UserDate) o;

        if (id != userDate.id) return false;
        if (username != null ? !username.equals(userDate.username) : userDate.username != null) return false;
        return email != null ? email.equals(userDate.email) : userDate.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
