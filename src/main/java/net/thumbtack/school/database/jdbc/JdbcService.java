package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class JdbcService  {
    public static void insertTrainee(Trainee trainee) throws SQLException {
        String insertQuery = "INSERT INTO trainee VALUES (?, ?, ?, ?, NULL)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, trainee.getId());
            stmt.setString(2, trainee.getFirstName());
            stmt.setString(3, trainee.getLastName());
            stmt.setInt(4, trainee.getRating());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                trainee.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void updateTrainee(Trainee trainee)  throws SQLException{

        String updateQuery = "UPDATE trainee SET trainee.first_name = ?, trainee.last_name = ?, trainee.rating = ? WHERE trainee.last_name = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, trainee.getFirstName());
            stmt.setString(2, trainee.getLastName());
            stmt.setInt(3, trainee.getRating());
            stmt.setString(4, "Иванов");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        Trainee trainee = null;
        String selectQuery = "SELECT trainee.id, trainee.first_name, trainee.last_name, trainee.rating FROM trainee WHERE id = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, traineeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int rating = rs.getInt("rating");
                trainee = new Trainee(id, firstName, lastName, rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        Trainee trainee = null;
        String selectQuery = "SELECT trainee.id, trainee.first_name, trainee.last_name, trainee.rating FROM trainee WHERE id = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, traineeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int rating = rs.getInt(4);
                trainee = new Trainee(id, firstName, lastName, rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        String selectQuery = "SELECT trainee.id, trainee.first_name, trainee.last_name, trainee.rating FROM trainee";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int rating = rs.getInt("rating");
               trainees.add(new Trainee(id, firstName, lastName, rating));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainees;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        String selectQuery = "SELECT trainee.id, trainee.first_name, trainee.last_name, trainee.rating FROM trainee";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int rating = rs.getInt(4);
                trainees.add(new Trainee(id, firstName, lastName, rating));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainees;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        String deleteQuery = "delete FROM trainee WHERE first_name = ? AND  last_name = ? AND rating = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.setString(1, trainee.getFirstName());
            stmt.setString(2, trainee.getLastName());
            stmt.setInt(3, trainee.getRating());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTrainees() throws SQLException {
        String deleteQuery = "TRUNCATE TABLE trainee ";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException {
        String insertQuery = "insert into ‘subject’ values(?, ?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, subject.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                subject.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
       Subject subject = null;
        String selectQuery = "SELECT ‘subject’.id, ‘subject’.subject_name FROM ‘subject’ WHERE id = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String subjectName = rs.getString("subject_name");
                subject = new Subject(id, subjectName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        Subject subject = null;
        String selectQuery = "SELECT ‘subject’.id, ‘subject’.subject_name FROM ‘subject’ WHERE id = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String subjectName = rs.getString(2);
                subject = new Subject(id, subjectName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    public static void deleteSubjects() throws SQLException {
        String deleteQuery = "DELETE FROM ‘subject’ ";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        String insertQuery = "insert into school VALUES (?, ?, ?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, school.getSchoolName());
            stmt.setInt(3, school.getYear());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                school.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        School school = null;
        String selectQuery = "SELECT school.id, school.school_name, school.year_study FROM school WHERE id = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String schoolName = rs.getString("school_name");
                int year = rs.getInt("year_study");
                school = new School(id, schoolName, year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return school;
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        School school = null;
        String selectQuery = "SELECT school.id, school.school_name, school.year_study FROM school WHERE id = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String schoolName = rs.getString(2);
                int year = rs.getInt(3);
                school = new School(id, schoolName, year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return school;
    }

    public static void deleteSchools() throws SQLException {
        String deleteQuery = "DELETE FROM school";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        String insertQuery = "INSERT INTO ‘group’ VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, group.getName());
            stmt.setString(3, group.getRoom());
            stmt.setInt(4, school.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                group.setId(rs.getInt(1));
                group.setSchoolsId(school.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        School school = null;
        String selectQuery = "SELECT school.id, school_name, year_study, ‘group’.id, ‘group’.group_name, ‘group’.room_name, ‘group’.schools_id from school inner join ‘group’ on school.id = ‘group’.schools_id where school.id = ?";
        Set<School> schoolSet = new HashSet<>();
        Set<Group> groupSet = new HashSet<>();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int schoolId = rs.getInt("id");
                String schoolName = rs.getString("school_name");
                int year = rs.getInt("year_study");
                schoolSet.add(new School(schoolId, schoolName, year));
                int groupId = rs.getInt("‘group’.id");
                String groupName = rs.getString("group_name");
                String roomName = rs.getString("room_name");
                int schoolsId = rs.getInt("schools_id");
                groupSet.add(new Group(groupId, groupName, roomName, schoolsId));
            }
            for (School school1: schoolSet) {
                for (Group group: groupSet) {
                    if (school1.getId() == group.getSchoolsId()) {
                        school1.addGroup(group);
                    }
                }
                 school = school1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        school.getGroups().sort(Comparator.comparingInt(Group::getId));
        return school;
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        List<School> schools = new ArrayList<>();
        String selectQuery = "SELECT school.id, school_name, year_study, ‘group’.id, ‘group’.group_name, ‘group’.room_name, ‘group’.schools_id from school inner join ‘group’ on school.id = ‘group’.schools_id";
        Set<School> schoolSet = new HashSet<>();
        Set<Group> groupSet = new HashSet<>();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int schoolId = rs.getInt("id");
                String schoolName = rs.getString("school_name");
                int year = rs.getInt("year_study");
                schoolSet.add(new School(schoolId, schoolName, year));
                int groupId = rs.getInt("‘group’.id");
                String groupName = rs.getString("group_name");
                String roomName = rs.getString("room_name");
                int schoolsId = rs.getInt("schools_id");
                groupSet.add(new Group(groupId, groupName, roomName, schoolsId));
            }
            for (School school: schoolSet) {
                for (Group group: groupSet) {
                    if (school.getId() == group.getSchoolsId()) {
                        school.addGroup(group);
                    }
                }
                school.getGroups().sort(Comparator.comparingInt(Group::getId));
                schools.add(school);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        schools.sort(Comparator.comparingInt(school -> school.getId()));
        return schools;
    }
}
