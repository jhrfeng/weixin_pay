package com.jing.weixin.utils;

import java.util.List;

public class SqlConvertHelper {
	/**
     * 去Sql中?为Null则去'?'及Object[]中对象(jdbcTemplate中传null参数会抛异常)
     * @param sql
     * @param args
     * @return
     */
    public static void sqlRemoveNull(StringBuffer sql,List para){
    	//非参数数据操作直接返回
    	if(para == null || sql == null)
    		return;
    	int index1 = para.indexOf(null);//空参数下标
    	int index2 = sql.indexOf("?");//占位符下标
//    	int index3 = sql.indexOf("'");//字符标识
    	int num = 0;//标识第几个?占位符
    	while(index2 > -1 && index1 > -1 )
    	{
    		if(num == index1){
    			para.remove(null);//集合中去NUll对象
    			sql.replace(index2, index2+1, "null");//sql中替换'?'
    			index1 = para.indexOf(null);
    			num--;
    		}
    		
    		index2 = sql.indexOf("?",index2+1);
    		num++;
    	}
    }
}
