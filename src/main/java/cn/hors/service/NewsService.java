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
     * 模糊查询标题和内容
     * @param news
     * @return
     */
    List<News> findAllByBlurry(News news);

    /**
     * 查询所有的公告信息
     * @param news
     * @return
     */
    List<News> findNoticeAll(News news);

    /**
     * 新增新闻
     * @param news 新闻bean
     * @return
     */
    boolean insert(News news);

    /**
     * 修改新闻
     * @param news 新闻bean
     * @return
     */
    boolean update(News news);

    /**
     * 删除新闻
     * @param ids 新闻id集合
     * @return
     */
    boolean deleteByIds(@Param("ids") Integer... ids);


}
