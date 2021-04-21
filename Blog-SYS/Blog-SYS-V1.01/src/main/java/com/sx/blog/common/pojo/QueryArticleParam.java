 package com.sx.blog.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 李金鑫
 * @date 2020/03/20
 */
 @Data
 @Accessors(chain=true)
 @AllArgsConstructor
 @NoArgsConstructor
public class QueryArticleParam {
    private String title;// 搜索
    private String tagId;// 标签id
    private Integer index;// 分页
}
