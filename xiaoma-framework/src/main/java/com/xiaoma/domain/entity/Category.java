package com.xiaoma.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 分类表(Catagory)表实体类
 *
 * @author makejava
 * @since 2023-10-23 00:09:40
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("xm_category")
public class Category {
    
    private Long id;
    //分类名
    private String name;
    //父类id
    private Long pid;
    //描述
    private String description;
    //状态：0-正常；1-禁用
    private Character status;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}

