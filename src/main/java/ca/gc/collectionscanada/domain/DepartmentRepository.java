package ca.gc.collectionscanada.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * This class is used to call database table DEPT, which will bring ALL the departments
 *  
 * @author khatrz
 *
 */
@Repository
public class DepartmentRepository extends AbstractDAOImpl<Department>
{
	@Autowired
	protected JdbcTemplate jdbc;
   
    @Override
    public Department[] retrieveArray()
    {
    	List<Department> deptList = jdbc.query("SELECT DEPT_NBR, DEPT_NAME_ENG, DEPT_NAME_FR FROM DEPT", departmentMapper);
		
        Department deptArray[] = new Department[deptList.size()];
        deptList.toArray(deptArray);
        return deptArray;
    }
    
    private static final RowMapper<Department> departmentMapper = new RowMapper<Department>() {
    	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
    		Department department = new Department();
    		department.setDeptID(rs.getString(1));
    		department.setDeptNameEng(rs.getString(2));
    		department.setDeptNameFr(rs.getString(3));
    		return department;
    	}
    };
}
