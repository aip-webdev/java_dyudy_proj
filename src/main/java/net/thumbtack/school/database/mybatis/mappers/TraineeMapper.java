package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

public interface TraineeMapper {

    @Insert("INSERT INTO trainee(first_name, last_name, rating, groups_id) VALUES " +
            "( #{trainee.firstName}, #{trainee.lastName}, #{trainee.rating}, #{group.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "trainee.id")
    Integer insert(@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Select("SELECT id, first_name as firstName, last_name as lastName, rating FROM trainee WHERE id = #{id} ")
    @Results({
            @Result(property = "id", column = "id")
    })
    Trainee getById(int id);

    @Select("SELECT id, first_name as firstName, last_name as lastName, rating FROM trainee WHERE groups_id = #{group.id} ")
    List<Trainee> getByGroup(@Param("group")Group group);

    @Select("SELECT id, first_name as firstName, last_name as lastName, rating FROM trainee")
    List<Trainee> getAll();

    @Update("UPDATE trainee SET first_name = #{trainee.firstName}, last_name = #{trainee.lastName}, rating = #{trainee.rating}  WHERE id = #{trainee.id} ")
    void update(@Param("trainee") Trainee trainee);

    @Select({"<script>",
            "SELECT id, first_name as firstName, last_name as lastName, rating FROM trainee",
            "<where>" +
                    "<if test='firstName != null'> first_name like #{firstName}",
            "</if>",
            "<if test='lastName != null'> AND last_name like #{lastName}",
            "</if>",
            "<if test='rating != null'> AND trainee.rating like #{rating}",
            "</if>",
            "</where>" +
                    "</script>"})
    @Results({
            @Result(property = "id", column = "id")
    })
    List<Trainee> getAllWithParams(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("rating") Integer rating);


    @Insert({"<script>",
            "INSERT INTO trainee (first_name, last_name, rating) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.firstName}, #{item.lastName}, #{item.rating} )",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(@Param("list") List<Trainee> trainees);

    @Delete("DELETE FROM trainee WHERE id = #{trainee.id}")
    void delete(@Param("trainee") Trainee trainee);

    @Delete("DELETE FROM trainee")
    void deleteAll();
}