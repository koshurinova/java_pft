package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate; //объект для делегирования

    public Groups(Groups groups) { //строим копию объекта
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate=new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate=new HashSet<GroupData>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded (GroupData group){
        Groups groups = new Groups(this); //создали копию
        groups.add(group); //добавляем объект, который передан в качестве параметра
        return groups;
    }

    public Groups withOut (GroupData group){
        Groups groups = new Groups(this); //создали копию
        groups.remove(group); //удаляем объект, который передан в качестве параметра
        return groups;
    }
}
