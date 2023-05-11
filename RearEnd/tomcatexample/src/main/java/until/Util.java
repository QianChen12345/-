package until;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Util {
	public String getnum() {
		Random random = new Random();
		String num = "";
		for (int i = 0; i <= 5; i++) {
			random.nextInt(9);
			num = num + random.nextInt(9);
		}
		return num;
	}

	public void connectdata(String ordernum ,String account,String price,String goodsid) {
		PreparedStatement pstmt = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/?serverTimezone=UTC&useSSL=false&useUnicode=true", "root", "123456");
			stmt = conn.createStatement();
			stmt.executeUpdate("USE items");
			
			pstmt = conn.prepareStatement("INSERT INTO orderlist VALUES(?,?,?,?)");
			pstmt.setString(1,ordernum);
			pstmt.setString(2, account);
			pstmt.setString(3, price);
			pstmt.setString(4, goodsid);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close(); // 关闭 ResultSet 对象
					pstmt = null;
				}
				if (stmt != null) {
					stmt.close(); // 关闭 Statement 对象
					stmt = null;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	 public static String MD5(String input) {
	        MessageDigest md5 = null;
	        try {
	            // 生成普通的MD5密码
	            md5 = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException e) {
	            return "check jdk";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "";
	        }
	        char[] charArray = input.toCharArray();
	        byte[] byteArray = new byte[charArray.length];
	        for (int i = 0; i < charArray.length; i++)
	            byteArray[i] = (byte) charArray[i];
	        byte[] md5Bytes = md5.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16)
	                hexValue.append("0");
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
	    }
	 

}
