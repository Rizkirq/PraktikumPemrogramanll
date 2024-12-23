package model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface UserMapper {
    @Insert("INSERT INTO users (name, email, phone, address) VALUES (#{name}, #{email}, #{phone}, #{address})")
    void insertUser(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email}, phone = #{phone}, address = #{address} WHERE email = #{email}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE email = #{email}")
    void deleteUser(String email);

    @Select("SELECT name, email, phone, address FROM users")
    List<User> getAllUsers();
}