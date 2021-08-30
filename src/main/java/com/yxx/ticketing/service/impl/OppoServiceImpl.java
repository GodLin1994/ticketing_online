package com.yxx.ticketing.service.impl;

import com.yxx.ticketing.dao.OppoMapper;
import com.yxx.ticketing.model.Oppo;
import com.yxx.ticketing.model.OppoExample;
import com.yxx.ticketing.service.OppoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 */
@Service
@Transactional
public class OppoServiceImpl implements OppoService {
    private final OppoMapper oppoMapper;

    @Autowired
    public OppoServiceImpl(OppoMapper oppoMapper) {
        this.oppoMapper = oppoMapper;
    }

    @Override
    public List<Oppo> findByUserId(int userId) {
        OppoExample example=new OppoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return oppoMapper.selectByExample(example);
    }

    @Override
    public int addOppo(Oppo oppo) {
        return oppoMapper.insertSelective(oppo);
    }
}
