package cn.edu.bupt.community.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "discusspost")
@Data
public class DiscussPost {
    @Id
    private int id; // 帖子id

    @Field(type = FieldType.Integer)
    private int userId; // 发帖用户id

    // 比如要存"互联网校招"，max拆分出最多的词来进行搜索，smart只拆出我们需要的词
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title; // 帖子标题

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content; // 帖子内容

    @Field(type = FieldType.Integer)
    private int type; // 帖子类型

    @Field(type = FieldType.Integer)
    private int status; // 帖子状态

    @Field(type = FieldType.Date)
    private Date createTime; // 发帖时间

    @Field(type = FieldType.Integer)
    private int commentCount; // 评论数量

    @Field(type = FieldType.Double)
    private double score; // 帖子分数
}
