package com.yxx.ticketing.dao;

import org.apache.ibatis.annotations.Param;

/**
 *
 *
 */
public interface WalletMapper {

    public int updateWalletBalanceById(@Param("wid") Integer wid, @Param("balance") double balance);
}
