package ca.gc.collectionscanada.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;

import ca.gc.collectionscanada.web.model.Department;

/**
 * This class is used to call database table DEPT, which will bring all the departments
 *  
 * @author khatrz
 *
 */
public class DepartmentDAO extends AbstractDAOImpl<Department>
{

	/**
	 * Default constructor
	 */
    public DepartmentDAO()
    {
    }

    public DepartmentDAO(DataSource dataSource)
    {
        super(dataSource);
    }
    
    @Override
    public Department[] retrieveArray()
    {
		Connection con = null;
		ResultSet rs = null;		
        String sql = "SELECT DEPT_NBR, DEPT_NAME_ENG, DEPT_NAME_FR FROM DEPT";
        ArrayList<Department> deptList = new ArrayList<Department>();
        
        try {
        con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next())
        {
        	Department dept = new Department();
            dept.setDeptID(rs.getString(1));
            dept.setDeptNameEng(rs.getString(2));
            dept.setDeptNameFr(rs.getString(3));
            deptList.add(dept);
        }
        }
        catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
        Department deptArray[] = new Department[deptList.size()];
        deptList.toArray(deptArray);
        return deptArray;
    }
}
