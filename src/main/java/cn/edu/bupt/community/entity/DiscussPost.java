package cn.edu.bupt.community.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DiscussPost {
    private int id; // 帖子id
    private int userId; // 发帖用户id
    private String title; // 帖子标题
    private String content; // 帖子内容
    private int type; // 帖子类型
    private int status; // 帖子状态
    private Date createTime; // 发帖时间
    private int commentCount; // 评论数量
    private double score; // 帖子分数
}
