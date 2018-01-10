package com.example.po.stadiummanagement3.step.utlis;
import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.List;

/**
 * 数据库操作类
 * Created by 田雍恺 on 2018/1/7.
 */

public class DbUtils {
    public static LiteOrm liteOrm;

    public static void createDb(Context context, String name) {
        name = name + ".db";
        if(liteOrm == null) {
            liteOrm = LiteOrm.newCascadeInstance(context, name);
            liteOrm.setDebugged(true);
        }
    }

    public static LiteOrm getLiteOrm(){
        return liteOrm;
    }

    /**
     * 插入一条数据
     * @param t
     * @param <T>
     */
    public static <T> void insert(T t) {
        liteOrm.save(t);
    }

    /**
     * 插入所有数据
     * @param list
     * @param <T>
     */
    public static <T> void insertAll(List<T> list) {
        liteOrm.save(list);
    }

    /**
     * 查询某字段等于value的值
     * @param cla
     * @param field
     * @param value
     * @param <T>
     * @return
     */
    public static <T> List<T> getQueryByWhere(Class<T> cla, String field, String[] value) {
        return liteOrm.<T>query(new QueryBuilder<T>(cla).where(field + "=?",value));
    }


    /**
     * 查询某字段等于value的值，可以指定分页
     * @param cla
     * @param field
     * @param value
     * @param start
     * @param length
     * @param <T>
     * @return
     */
    public static <T> List<T> getQueryByWhereLength(Class<T> cla, String field, String[] value, int start, int length) {
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value).limit(start, length));
    }


    /**
     * 删除所有数据
     * @param cla
     * @param <T>
     */
    public static <T> void deleteAll(Class<T> cla) {
        liteOrm.deleteAll(cla);
    }


    /**
     * 仅在存在时更新
     * @param t
     * @param <T>
     */
    public static <T> void update(T t) {
        liteOrm.update(t, ConflictAlgorithm.Replace);
    }

    public static <T> void updateAll(List<T> list) {
        liteOrm.update(list);
    }

    public static void closeDb(){
        liteOrm.close();
    }
}