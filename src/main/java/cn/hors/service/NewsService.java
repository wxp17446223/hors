package cn.hors.service;

import cn.hors.bean.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsService{

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
     * 查询浏览次数
     * @param newId
     * @return
     * @throws Exception
     */
    Integer scanCounter(Integer newId);

    /**
     * 模糊查询主题和内容
     * @param name 输入
     * @return 新闻集合
     */
    List<News> findByTitleOrContent(String name);

    /**
     * 查询所有的公告信息
     * @param news
     * @return
     */
    List<News> findNoticeAll(News news);
}
