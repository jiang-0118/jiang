package com.softeem.mapper;


import com.softeem.entity.TFeeds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-07-17
 */
public interface TFeedsMapper{

    int addFeeds(TFeeds feeds);

    List<TFeeds> findFeedsById(Integer followingDinerId);
}
