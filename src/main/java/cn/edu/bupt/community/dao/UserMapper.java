package cn.edu.bupt.community.dao;

import cn.edu.bupt.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(@Param("id") int id, @Param("status") int status);// 0-未激活，1-已激活

    int updateHeader(@Param("id") int id, @Param("headerUrl") String headerUrl); // 更新头像

    int updatePassword(@Param("id") int id, @Param("password") String password); // 更新密码
}
