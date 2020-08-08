package cn.hors.service.impl;

import cn.hors.bean.Timeline;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.mapper.TimelineMapper;
import cn.hors.service.TimelineService;

import java.util.List;

@Service
public class TimelineServiceImpl implements TimelineService{

    @Resource
    private TimelineMapper timelineMapper;

    @Override
    public boolean insert(Timeline timeline) {
        return timelineMapper.insert(timeline)>0;
    }

    @Override
    public boolean delByIds(Integer... ids) {
        return timelineMapper.delByIds(ids)>0;
    }

    @Override
    public boolean update(Timeline timeline) {
        return timelineMapper.update(timeline)>0;
    }

    @Override
    public List<Timeline> findAll(Timeline timeline) {
        return timelineMapper.findAll(timeline);
    }

    @Override
    public Timeline findById(Integer id) {
        return timelineMapper.findById(id);
    }

    @Override
    public List<Timeline> findByDoctorId(Integer doctorId) {
        return timelineMapper.findByDoctorId(doctorId);
    }

    @Override
    public List<Timeline> findByDoctorIdAndDate(Integer doctorId, String date) {
        return timelineMapper.findByDoctorIdAndDate(doctorId,date);
    }

    @Override
    public boolean updateQuota(Integer tId) {
        return timelineMapper.updateQuota(tId)>0;
    }

    @Override
    public Timeline findByDoctorIdAndDateAndStartTime(Integer doctorId, String date, String start0Time) {
        return timelineMapper.findByDoctorIdAndDateAndStartTime(doctorId, date, start0Time);
    }


}
