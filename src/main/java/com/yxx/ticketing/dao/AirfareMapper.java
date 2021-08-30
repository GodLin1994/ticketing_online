package com.yxx.ticketing.dao;

import com.yxx.ticketing.model.Airfare;
import com.yxx.ticketing.model.AirfareExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AirfareMapper {
    long countByExample(AirfareExample example);

    int deleteByExample(AirfareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Airfare record);

    int insertSelective(Airfare record);

    List<Airfare> selectByExample(AirfareExample example);

    Airfare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Airfare record, @Param("example") AirfareExample example);

    int updateByExample(@Param("record") Airfare record, @Param("example") AirfareExample example);

    int updateByPrimaryKeySelective(Airfare record);

    int updateByPrimaryKey(Airfare record);

    //lyz

    public int updateAirfareByOrderId(@Param("oid") Integer oid, @Param("fid") Integer fid);

    public List<Airfare> getAirfareByOidAndFid(@Param("oid") Integer oid, @Param("fid") Integer fid);

    public List<Airfare> selectAirfaresToUpgrade(@Param("fid") Integer fid,@Param("lv") String lv);

    public int updateAirfareByid(@Param("afid") Integer afid,@Param("opid") Integer opid,@Param("oid") Integer oid);
}