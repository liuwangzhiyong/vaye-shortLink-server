package com.sl.shortLink.common.basic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author wangzhiyong
 * @date 2021年12月18日 上午11:44
 */
@Data
public class BaseModel extends AbstractModel{
    //主键id
    @TableId(type = IdType.AUTO)
    private Long id;
}
