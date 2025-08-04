package com.zsq.learnspringboot.pojo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageQuery {
    @NotNull
    private Long pageNum = 1L;   // 第几页
    @NotNull
    private Long pageSize   = 10L;   // 每页条数
    // 其他查询条件可继续追加
    private Integer categoryId;
    private String state;
}