package net.thumbtack.school.database.model;



import java.util.*;

public class Group {
    private int id;
    private String name;
    private String room;
    private List<Trainee> trainees;
    private List<Subject> subjects;
    private int schoolsId;


    public Group() {
    }

    public Group(int id, String name, String room, List<Trainee> trainees, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.trainees = trainees;
        this.subjects = subjects;
    }

    public Group(int id, String name, String room) {
        this.id = id;
        this.name = name;
        this.room = room;
        trainees = new ArrayList<>();
        subjects = new ArrayList<>();
    }
    public Group(int id, String name, String room, int schoolsId) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.schoolsId = schoolsId;
        trainees = new ArrayList<>();
        subjects = new ArrayList<>();
    }

    public Group(String name, String room) {
        this.id = 0;
        this.name = name;
        this.room = room;
        trainees = new ArrayList<>();
        subjects = new ArrayList<>();
    }

    public void setSchoolsId(int schoolsId) {
        this.schoolsId = schoolsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSchoolsId() {
        return schoolsId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) {
        Iterator<Trainee> iterator = trainees.iterator();
        int a = 0;
        while (iterator.hasNext())
        {
            if (trainee == iterator.next()){
                a =1;
                iterator.remove();
            }
        }
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        Iterator<Subject> iterator = subjects.iterator();
        int a = 0;
        while (iterator.hasNext())
        {
            if (subject == iterator.next()){
                a =1;
                iterator.remove();
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getId() == group.getId() &&
                getSchoolsId() == group.getSchoolsId() &&
                Objects.equals(getName(), group.getName()) &&
                Objects.equals(getRoom(), group.getRoom()) &&
                Objects.equals(getTrainees(), group.getTrainees()) &&
                Objects.equals(getSubjects(), group.getSubjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRoom(), getTrainees(), getSubjects(), getSchoolsId());
    }}





