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
     *模糊查询主题和内容
     * @param name 输入
     * @return 新闻集合
     */
    List<News> findByTitleOrContent(@Param("name") String name);


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
    int insert(News news);

    /**
     * 修改新闻
     * @param news 新闻bean
     * @return
     */
    int update(News news);

    /**
     * 删除新闻
     * @param ids 新闻id集合
     * @return
     */
    int deleteByIds(@Param("ids") Integer... ids);
}