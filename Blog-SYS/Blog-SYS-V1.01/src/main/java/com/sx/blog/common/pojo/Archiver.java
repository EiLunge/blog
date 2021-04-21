 package com.sx.blog.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 李金鑫
 * @date 2020/01/19
 */
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Archiver {
    private String yearMonth;
    private Integer count;
    // 修改后的格式
    private String yearMonthStr;
}
