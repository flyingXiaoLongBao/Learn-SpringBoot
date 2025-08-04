package com.zsq.learnspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @TableId(type = IdType.AUTO)
    private Integer id;//主键ID

    private String categoryName;//分类名称

    private String categoryAlias;//分类别名

    private Integer createUser;//创建人ID

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间
}
