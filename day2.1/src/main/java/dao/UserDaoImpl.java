package dao;

import static utils.DBUtils.closeConnection;
import static utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// fields
	private Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pst2;

	public UserDaoImpl() throws SQLException {
		// open conn
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		pst2 = cn.prepareStatement("insert into users(first_name,last_name,email,password,dob,status,role) values(?,?,?,?,?,0,'voter')");
		System.out.println("user dao created!");
	}

	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);		
		pst1.setString(2, password);
		try(ResultSet rst=pst1.executeQuery()) {
			if(rst.next()) //=> success
				/*
				 * int id, String firstName, String lastName, String email, String password, Date dob,
			boolean votingStatus, String role
				 */
				return new User( rst.getString(2), rst.getString(3), email, password, 
						rst.getDate(6), rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		closeConnection();
		System.out.println("user dao cleaned up!");
	}

	@Override
	public boolean addUser(String firstName, String lastName, String email, String password, Date dob) throws SQLException {
	  LocalDate dateOfBirth = dob.toLocalDate();
	  LocalDate currentDate = LocalDate.now();
	  Period age = Period.between(dateOfBirth, currentDate);
	  if (age.getYears() < 21) {
	    return false;
	  } else {
	    pst2.setString(1, firstName);
	    pst2.setString(2, lastName);
	    pst2.setString(3, email);
	    pst2.setString(4, password);
	    pst2.setDate(5, dob);

	    int rowsAffected = pst2.executeUpdate();
	    if( rowsAffected > 0)
	    {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	  }
	}


	

}
