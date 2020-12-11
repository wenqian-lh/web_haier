package com.web.haier.dao.impl;

import java.util.List;

import com.web.haier.bean.TypeInfo;
import com.web.haier.dao.DBHelper;
import com.web.haier.dao.ITypeInfoDao;

/**
 * 父分类 数据模型层 实现
 * @author admin
 *
 */
public class TypeInfoDaoImpl implements ITypeInfoDao {

	@Override
	public int add(TypeInfo tf) {
		DBHelper db = new DBHelper();
		String sql = "insert into typeinfo values(0, ?)";
		return db.update(sql, tf.getTname());
	}

	@Override
	public int update(TypeInfo tf) {
		DBHelper db = new DBHelper();
		String sql = "update typeinfo set tname=? where tid=?";
		return db.update(sql, tf.getTname(), tf.getTid());
	}

	@Override
	public List<TypeInfo> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select tid, tname from typeinfo order by tid";
		return db.finds(TypeInfo.class, sql);
	}

	@Override
	public int total() {
		DBHelper db = new DBHelper();
		String sql = "select count(tid) from typeinfo";
		return db.total(sql);
	}

	@Override
	public List<TypeInfo> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select tid, tname from typeinfo order by tid limit ?, ?";	// 注意: 从哪一条记录开始查    查多少条数据
		return db.finds(TypeInfo.class, sql, (page - 1) * rows, rows);
	}

	@Override
	public int delete(TypeInfo tf) {
		DBHelper db = new DBHelper();
		String sql = "delete from typeinfo where tid=?";
		return db.update(sql, tf.getTid());
	}
	
}
