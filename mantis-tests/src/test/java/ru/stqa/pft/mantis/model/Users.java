package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserDate> {

    private Set<UserDate> delegate; //объект для делегирования

    public Users(Users users) { //строим копию объекта
        this.delegate = new HashSet<UserDate>(users.delegate);
    }

    public Users() {
        this.delegate=new HashSet<UserDate>();
    }

    public Users(Collection<UserDate> users) {
        this.delegate=new HashSet<UserDate>(users);
    }

    @Override
    protected Set<UserDate> delegate() {
        return delegate;
    }


}
