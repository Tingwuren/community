package cn.edu.bupt.community.dao;

import cn.edu.bupt.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
@Deprecated
public interface LoginTicketMapper {
    @Insert({
        "insert into login_ticket (user_id, ticket, status, expired) ",
        "values (#{userId}, #{ticket}, #{status}, #{expired})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id") // 自动生成主键
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
        "select id, user_id, ticket, status, expired ",
        "from login_ticket where ticket = #{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    @Update({
            "<script>",
            "update login_ticket set status = #{status} where ticket = #{ticket}",
            "<if test=\"ticket!=null\">",
            "and 1=1 ",
            "</if>",
            "</script>"
    })
    int updateStatus(@Param("ticket") String ticket, @Param("status")int status);
}
