package me.spring.boot.data.biz;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 通过jdbc方法映射的用户实体类
 *
 * @author baiyu
 * <p>
 * Table=al_user_info
 */
public class JdbcUserInfo implements Serializable {

    private int userId;
    private String userName;
    private String userAccount;
    private String userPassword;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JdbcUserInfo userInfo = (JdbcUserInfo) o;

        if (userId != userInfo.userId) return false;
        if (userName != null ? !userName.equals(userInfo.userName) : userInfo.userName != null) return false;
        if (userAccount != null ? !userAccount.equals(userInfo.userAccount) : userInfo.userAccount != null)
            return false;
        return userPassword != null ? userPassword.equals(userInfo.userPassword) : userInfo.userPassword == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JdbcUserInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }


    public static class JdbcUserInfoRowMapper implements RowMapper<JdbcUserInfo> {
        @Override
        public JdbcUserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            JdbcUserInfo userInfo = new JdbcUserInfo();
            userInfo.userId = rs.getInt("u_id");
            userInfo.userName = rs.getString("u_name");
            userInfo.userAccount = rs.getString("u_account");
            userInfo.userPassword = rs.getString("u_password");
            return userInfo;
        }
    }
}
