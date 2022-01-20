package net.thumbtack.school.database.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class School {
    private int id;
    private String schoolName;
    private int year;
    private List<Group> groups;

    public School() {
    }

    public School(String schoolName, int year)  {
        this.id = 0;
        this.schoolName = schoolName;
        this.year = year;
        groups = new ArrayList<>();
    }

    public School(int id, String schoolName, int year) {
        this.id = id;
        this.schoolName = schoolName;
        this.year = year;
        groups = new ArrayList<>();
    }

    public School(int id, String schoolName, int year, List<Group> groups) {
        this.id = id;
        this.schoolName = schoolName;
        this.year = year;
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void removeGroup(Group group)    {
        Iterator<Group> iterator = groups.iterator();
        int a = 0;
        while (iterator.hasNext())
        {
            if (group == iterator.next()){
                a =1;
                iterator.remove();
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        School school = (School) o;
        return getId() == school.getId() &&
                getYear() == school.getYear() &&
                Objects.equals(getSchoolName(), school.getSchoolName()) &&
                Objects.equals(getGroups(), school.getGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSchoolName(), getYear(), getGroups());
    }
}
