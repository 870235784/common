package com.tca.common.data.mybatis.test.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tca.common.data.mybatis.test.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhouan123
 * @since 2021-07-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
