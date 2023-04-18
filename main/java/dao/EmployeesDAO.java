package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.EmployeesVO;

public class EmployeesDAO {
	private EmployeesDAO() {
	}

	private static EmployeesDAO instance = new EmployeesDAO();

	public static EmployeesDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		return conn;
	}

	public int userCheck(String id, String pass, String lev) {
		int result = 1;
		String sql = "select * from employees where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){ // 해당 아이디가 DB에 존재할 때
				//비밀번호가 일치하고
				if(pass.equals(rs.getString("pass"))){
					//회원등급이 일치하면
					if(lev.equals(rs.getString("lev"))){
//						if(lev.equals("A"))
						result = 2; //운영자로 로그인 성공
						if(lev.equals("B")){ // 레벨이 B이면 result = 3으로 변경
							result=3;//일반 회원으로 로그인 성공
						}
					} else { // 레벨이 일치하지 않을 경우 로그인 실패
						result =1;
					}
				} else {  //비밀번호가 일치하지 않을 경우 로그인 실패
					result = 0;
				}
			} else {  // 아이디가 일치하지 않을 경우 로그인 실패
				result = -1;
			}
		 }catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				} 
			}
		return result;
		
	}
	
	// 아이디로 회원 정보 가져오는 메서드
		public EmployeesVO getMember(String id) {
			EmployeesVO evo = null;
			String sql = "select * from employees where id=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					evo = new EmployeesVO();
					evo.setId(rs.getString("id"));
					evo.setPass(rs.getString("pass"));
					evo.setName(rs.getString("name"));
					evo.setLev(rs.getString("lev"));
				    evo.setEnter(rs.getDate("enter"));
					evo.setGender(rs.getInt("gender"));
					evo.setPhone(rs.getString("phone"));	
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return evo;
		}
	
	public void insertMember(EmployeesVO evo) {
		String sql = "insert into employees values(?, ?, ?, ?, sysdate, ?, ?)";		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			// ?에 값 세팅
			pstmt.setString(1, evo.getId());
			pstmt.setString(2, evo.getPass());
			pstmt.setString(3, evo.getName());
			pstmt.setString(4, evo.getLev());			
			pstmt.setInt(5, evo.getGender());
			pstmt.setString(6, evo.getPhone());
			System.out.println(pstmt.executeUpdate());			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

	public int updateMember(EmployeesVO evo) {
		int result = -1;
		String sql = "update employees set pass=?, name=?, lev=?, gender=?, phone=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, evo.getPass());
			pstmt.setString(2, evo.getName());
			pstmt.setString(3, evo.getLev());
			pstmt.setInt(4, evo.getGender());
			pstmt.setString(5, evo.getPhone());
			pstmt.setString(6, evo.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
