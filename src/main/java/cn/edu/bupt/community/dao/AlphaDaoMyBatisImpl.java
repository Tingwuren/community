package cn.edu.bupt.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository // 交给Spring容器管理
@Primary // 优先注入
public class AlphaDaoMyBatisImpl implements  AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
