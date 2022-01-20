package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Set;

public interface GroupMapper {
    @Insert("INSERT INTO ‘group’(group_name, room_name, schools_id) VALUES" +
            "(#{group.name}, #{group.room}, #{school.id} )" )
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    Integer insert(@Param("school") School school, @Param("group") Group group);

    @Update("UPDATE ‘group’ SET ‘group’.group_name =  #{group.name}, ‘group’.room_name = #{group.room} WHERE  ‘group’.id = #{group.id}  ")
    void update(@Param("group")Group group);

    @Select("SELECT id, group_name as name, room_name as room FROM ‘group’")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup", fetchType = FetchType.LAZY) ),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup", fetchType = FetchType.LAZY))})
    List<Group> getAll();

    @Select("SELECT id, group_name as name, room_name as room FROM ‘group’ WHERE schools_id = #{school.id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup", fetchType = FetchType.LAZY) ),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup", fetchType = FetchType.LAZY))})
    List<Group> getBySchool (@Param("school") School school);


    @Delete("DELETE FROM ‘group’ WHERE id = #{group.id} ")
    void delete(@Param("group") Group group);

    @Update("UPDATE trainee SET groups_id =  #{group.id} WHERE  first_name = #{trainee.firstName} AND last_name = #{trainee.lastName}")
    void moveTraineeToGroup(@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Update("UPDATE trainee SET groups_id =  NULL WHERE  first_name = #{trainee.firstName} AND last_name = #{trainee.lastName}")
    void deleteTraineeFromGroup(@Param("trainee") Trainee trainee);

    @Insert("INSERT INTO group_subject(group_id, subject_id) VALUES" + "(#{group.id}, #{subject.id})" )
    @Options(useGeneratedKeys = true)
    void addSubjectToGroup(@Param("group") Group group, @Param("subject")Subject subject);
}
