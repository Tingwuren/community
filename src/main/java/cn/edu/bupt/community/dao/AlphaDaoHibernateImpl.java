package cn.edu.bupt.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate") // 交给Spring容器管理
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
