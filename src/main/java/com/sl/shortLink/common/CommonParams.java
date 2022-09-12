package com.sl.shortLink.common;

import com.sl.shortLink.common.basic.AbstractModel;
import lombok.Data;

/**
 * @author wangzhiyong
 * @date 2022年03月13日 上午10:46
 */
@Data
public class CommonParams extends AbstractModel {

    private Long uid;

    private String role;

    public void init(Long uid,String role){
        this.uid = uid;
        this.role = role;
    }
}
