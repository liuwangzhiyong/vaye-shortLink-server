package com.sl.shortLink.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sl.shortLink.entity.ShortLink;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (ShortLink)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-12 16:17:09
 */
public interface ShortLinkDao extends BaseMapper<ShortLink> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShortLink queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param shortLink 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<ShortLink> queryAllByLimit(ShortLink shortLink, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param shortLink 查询条件
     * @return 总行数
     */
    long count(ShortLink shortLink);

    /**
     * 新增数据
     *
     * @param shortLink 实例对象
     * @return 影响行数
     */
    int insert(ShortLink shortLink);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShortLink> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ShortLink> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShortLink> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ShortLink> entities);

    /**
     * 修改数据
     *
     * @param shortLink 实例对象
     * @return 影响行数
     */
    int update(ShortLink shortLink);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

