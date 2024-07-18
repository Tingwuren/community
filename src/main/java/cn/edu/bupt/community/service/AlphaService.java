package cn.edu.bupt.community.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("singleton") // 默认为singleton
// @Scope("prototype") // 多例
public class AlphaService {

    // 构造方法
    public AlphaService() {
        System.out.println("构造AlphaService");
    }

    @PostConstruct // 在构造方法之后执行
    public void construct() {
        System.out.println("AlphaService构造成功");
    }

    @PreDestroy // 在销毁之前执行
    public void destroy() {
        System.out.println("销毁AlphaService");
    }
}
