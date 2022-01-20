package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Set;

public interface SubjectMapper {
    @Delete("DELETE FROM ‘subject’")
    void deleteAll();

    @Insert("INSERT INTO ‘subject’ (subject_name) VALUES" + "( #{name} )")
    @Options(useGeneratedKeys = true)
    void insert(Subject subject);

    @Select("SELECT id, subject_name as name FROM ‘subject’ WHERE id = #{id} ")
    @Results({
            @Result(property = "id", column = "id")
    })
    Subject getById(int id);

    @Select("SELECT id, subject_name as name FROM ‘subject’")
    @Results({
            @Result(property = "id", column = "id")
    })
    List<Subject> getAll();

    @Update("UPDATE ‘subject’ SET subject_name = #{subject.name}  WHERE id = #{subject.id}")
    void update(@Param("subject") Subject subject);

    @Delete("DELETE FROM ‘subject’ WHERE id = #{subject.id}")
    void delete(@Param("subject")Subject subject);

    @Select("SELECT id, subject_name as name FROM ‘subject’ WHERE id IN(SELECT subject_id from group_subject WHERE group_id = #{group.id})  ")
   List<Subject> getByGroup(@Param("group")Group group);


}
