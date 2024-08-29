package cn.edu.bupt.community.service;

import cn.edu.bupt.community.dao.DiscussPostMapper;
import cn.edu.bupt.community.dao.UserMapper;
import cn.edu.bupt.community.entity.DiscussPost;
import cn.edu.bupt.community.entity.User;
import cn.edu.bupt.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Service
@Scope("singleton") // 默认为singleton
// @Scope("prototype") // 多例
public class AlphaService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

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

    // REQUIRED: 如果当前存在事务，那么就使用当前的事务，如果当前没有事务，就新建一个事务
    // REQUIRED_NEW: 总是新建事务
    // NESTED: 如果当前存在事务，则在嵌套事务内执行
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object save1() {
        // addUser
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
        user.setEmail("alpha@qq.com");
        user.setHeaderUrl("http://image.nowcoder.com/head/1.png");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        // addDiscuss
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(user.getId());
        discussPost.setTitle("hello");
        discussPost.setContent("world");
        discussPost.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(discussPost);

        Integer.valueOf("abc");

        return "ok";
    }

    public Object save2() {
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                // addUser
                User user = new User();
                user.setUsername("alpha");
                user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
                user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
                user.setEmail("alpha@qq.com");
                user.setHeaderUrl("http://image.nowcoder.com/head/1.png");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);

                // addDiscuss
                DiscussPost discussPost = new DiscussPost();
                discussPost.setUserId(user.getId());
                discussPost.setTitle("hello");
                discussPost.setContent("world");
                discussPost.setCreateTime(new Date());
                discussPostMapper.insertDiscussPost(discussPost);

                Integer.valueOf("abc");

                return "ok";
            }
        });
    }
}
