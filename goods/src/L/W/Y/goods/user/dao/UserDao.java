package L.W.Y.goods.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import L.W.Y.goods.user.domain.User;

import cn.itcast.jdbc.TxQueryRunner;

/*
 * �û�ģ��־ò�
 * 
 */

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	
	/**
	 * У���û����Ƿ�ע��
	 * @param loginname
	 * @return
	 * @throws SQLException 
	 */
	public boolean ajaxValidateLoginname(String loginname) throws SQLException {
		String sql = "select count(1) from t_user where loginname=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), loginname);
		return number.intValue() == 0;
		
	}
	
	/**
	 * У��Email�Ƿ�ע��
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public boolean ajaxValidateEmail(String email) throws SQLException {
		String sql = "select count(1) from t_user where email=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), email);
		return number.intValue() == 0;
	}
	
	
	public void add(User user) throws SQLException{
		String sql = "insert into t_user values(?,?,?,?,?,?,?)";
		
		Object[] params = {user.getUid(),user.getLoginname(),user.getEmail(),user.getPhone(),
				user.getLoginpass(),user.isStatus(),user.getActivationCode()	};
		qr.update(sql,params);
	}
}
