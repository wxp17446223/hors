package cn.hors.service.impl;

import cn.hors.bean.News;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.mapper.NewsMapper;
import cn.hors.service.NewsService;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Resource
    private NewsMapper newsMapper;


    @Override
    public List<News> findNewsAll(News news) {
        return newsMapper.findNewsAll(news);
    }

    @Override
    public News findByNewId(Integer newId) {
        return newsMapper.findByNewId(newId);
    }

    @Override
    public Integer scanCounter(Integer newId)  {
        Integer counter=newsMapper.findByNewId(newId).getCount();
        int i  = ++counter;
        newsMapper.updateCount(newId, i);
        return i;
    }

    @Override
    public List<News> findByTitleOrContent(String name) {
        return newsMapper.findByTitleOrContent(name);
    }

    @Override
    public List<News> findAllByBlurry(News news) {
        return newsMapper.findAllByBlurry(news);
    }

    @Override
    public List<News> findNoticeAll(News news) {
        return newsMapper.findNoticeAll(news);
    }

    @Override
    public boolean insert(News news) {
        return newsMapper.insert(news)>0?true:false;
    }

    @Override
    public boolean update(News news) {
        return newsMapper.update(news)>0?true:false;
    }

    @Override
    public boolean deleteByIds(Integer... ids) {
        return newsMapper.deleteByIds(ids)>0?true:false;
    }

}
