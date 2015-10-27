package ca.gc.collectionscanada.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ca.gc.collectionscanada.model.Department;
import ca.gc.collectionscanada.model.DepartmentWiseURLs;
import ca.gc.collectionscanada.model.SingleURL;

/**
 * This class is used to join DEPT and SEED_LIST table also DEPT_WISE_SEED,
 * which will bring all the departments WITH their related urls
 * @author khatrz
 *
 */
@Repository
public class DepartmentWiseURLDAO  extends AbstractDAOImpl<DepartmentWiseURLs> 
{
	
	@Autowired
	protected JdbcTemplate jdbc;
	
	@Override
	public DepartmentWiseURLs [] retrieveArray() 
	{
	
		List<DepartmentWiseURLs> deptWiseList = jdbc.query(
				"SELECT deps.DEPT_NBR, deps.dept_name_eng, deps.DEPT_NAME_FR, se.SEED_DSC, se.SEED_NBR FROM dept deps, SEED_LIST se, (SELECT dept_nbr, seed_nbr  FROM dept_wise_seed  GROUP BY dept_nbr,  seed_nbr  ORDER BY dept_nbr ) t WHERE t.dept_nbr = deps.dept_nbr AND t.seed_nbr  = se.seed_nbr",
				new ResultSetExtractor<List<DepartmentWiseURLs>>() {
					@Override
					public List<DepartmentWiseURLs> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<DepartmentWiseURLs> list = new ArrayList<DepartmentWiseURLs>();
						List<SingleURL> urlList = null;
						SingleURL singleURL = null;
						Integer lastdeptId = 0;
						DepartmentWiseURLs dwu = null;
						while (rs.next()) {
							singleURL = new SingleURL();
							singleURL.setUrlId(rs.getString(5));
							singleURL.setUrlDesc(rs.getString(4));	
							
							if (lastdeptId != rs.getInt(1)) {
								
								//Initialize
								urlList = new ArrayList<SingleURL>();

								dwu = new DepartmentWiseURLs();					

								dwu.setDeptId(rs.getString(1));
								dwu.setEngDesc(rs.getString(2));
								dwu.setFrDesc(rs.getString(3));
								dwu.setUrlDesc(rs.getString(4));
								dwu.setUrlId(rs.getString(5));
							} 
							

							urlList.add(singleURL);				
							Collections.sort(urlList, new Comparator<SingleURL>() {
								@Override
								public int compare(SingleURL o1, SingleURL o2) {
									return o1.getUrlDesc().compareTo(o2.getUrlDesc());
								}					
							});
							
							SingleURL [] urlArray = new SingleURL[urlList.size()];
							urlList.toArray(urlArray);
							dwu.setUrlArray(urlArray);
							
							if (lastdeptId != rs.getInt(1)) {
								//Total roughly 166 unique departments will be added
								list.add(dwu);
							}
							
							lastdeptId = rs.getInt(1);
						}
						return list;
					}
					
				}
		);
		
	
		DepartmentWiseURLs [] masterArray = new DepartmentWiseURLs [deptWiseList.size()];
		deptWiseList.toArray(masterArray);		//copy to array	
		return masterArray;
	}
}
