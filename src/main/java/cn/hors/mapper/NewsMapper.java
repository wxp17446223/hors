package cn.hors.mapper;

import cn.hors.bean.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    /**
     * 查询所有新闻信息
     * @param news
     * @return
     */
    List<News> findNewsAll(News news);

    /**
     * 通过newId查询单条新闻信息
     * @param newId
     * @return
     */
    News findByNewId(Integer newId);

    /**
     * 更新单条新闻的浏览量
     * @param newId
     * @param count
     */
    void updateCount( @Param("newId")Integer newId, @Param("count") Integer count);

    /**
     * 查询所有公告
     * @param news
     * @return
     */
    List<News> findNoticeAll(News news);

}