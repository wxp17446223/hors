package cn.hors.service;

import cn.hors.bean.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsService{

    List<News> findNewsAll(News news);

    News findByNewId(Integer newId);

    /**
     * 查询浏览次数
     * @param newId
     * @return
     * @throws Exception
     */
    Integer scanCounter(Integer newId);


}
