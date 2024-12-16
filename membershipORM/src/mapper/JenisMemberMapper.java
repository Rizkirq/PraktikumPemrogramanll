package mapper;

import model.JenisMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface JenisMemberMapper {
    @Insert("INSERT INTO jenis_member (id, nama) VALUES (#{id}, #{nama})")
    int insert(JenisMember jenisMember);

    @Update("UPDATE jenis_member SET nama = #{nama} WHERE id = #{id}")
    int update(JenisMember jenisMember);

    @Delete("DELETE FROM jenis_member WHERE id = #{id}")
    int delete(JenisMember jenisMember);

    @Select("SELECT * FROM jenis_member")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "nama", column = "nama")
    })
    List<JenisMember> findAll();
}
