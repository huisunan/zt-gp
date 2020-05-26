package com.zt.gp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zt
 * @since 2020-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Task implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 图片id，逗号隔开
     */
    private String pictureId;

    /**
     * 用户的id
     */
    private Integer userId;

    /**
     * 0:建立 1：运行 2:运行结束
     */
    private Integer status;

    /**
     * 运行结果的文件夹
     */
    private String result;

    @TableField(exist = false)
    private List<Picture> pictures;
}
