package com.junly.mybatis.dao;

import com.junly.mybatis.beans.NameSing;
import com.junly.mybatis.mapper.NameSingMapper;
import com.junly.mybatis.sqlsession.MybatisSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class NameSignDao {
    SqlSession session = MybatisSessionUtil.getSession();

    // 使用接口方式
    public int insert(NameSing user){
        NameSingMapper nameSingMapper = session.getMapper(NameSingMapper.class);
        int rint = nameSingMapper.insert(user);
        session.commit();
        return rint;
    }
}
