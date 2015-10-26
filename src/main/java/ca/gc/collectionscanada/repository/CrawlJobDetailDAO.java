package ca.gc.collectionscanada.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrawlJobDetailDAO {
	/**
	 * Default constructor
	 */
	public CrawlJobDetailDAO() {		
	}
	

	public static List <String> retrieveCrawlJobList(String themeNbr) throws ClassNotFoundException, SQLException {
		//String sql = "SELECT deps.DEPT_NBR, deps.dept_name_eng, deps.DEPT_NAME_FR, se.SEED_DSC, se.SEED_NBR FROM dept deps, SEED_LIST se, (SELECT dept_nbr, seed_nbr  FROM dept_wise_seed  GROUP BY dept_nbr,  seed_nbr  ORDER BY dept_nbr ) t WHERE t.dept_nbr = deps.dept_nbr AND t.seed_nbr  = se.seed_nbr";
		String sql = "SELECT DISTINCT t.crawl_job_nbr FROM theme th,  crawl_job_theme crawltheme,  crawl_job cra, crawl_job_qa_stus status, (SELECT DISTINCT cr.CRAWL_JOB_SEED_RDRCT_URL_TXT,  se.SEED_NBR,   se.SEED_URL_TXT,  cr.CRAWL_JOB_NBR,  cr.CRAWL_JOB_SEED_SCOPE_TXT,  cr.SEED_QA_STUS_NBR,  cj.crawl_job_nme,  cj.CRAWL_JOB_QA_STUS_NBR,  cj.CRAWL_JOB_STRT_DTE FROM crawl_job_seed cr,  seed se,  crawl_job cj  WHERE cr.seed_nbr = se.seed_nbr  AND cr.CRAWL_JOB_NBR  = cj.CRAWL_JOB_NBR  ORDER BY se.SEED_URL_TXT,    cr.CRAWL_JOB_NBR ASC ) t WHERE t.CRAWL_JOB_NBR = crawltheme.CRAWL_JOB_NBR AND cra.crawl_job_nme = t.crawl_job_nme AND th.THEME_NBR      = crawltheme.THEME_NBR AND cra.crawl_job_nbr = crawltheme.CRAWL_JOB_NBR and t.CRAWL_JOB_QA_STUS_NBR = status.crawl_job_qa_stus_nbr AND th.theme_nbr = " +  Integer.parseInt(themeNbr) + " ORDER BY t.crawl_job_nbr";
		
		System.out.println(sql);
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//For test environment use the following connection object
		
/*		Connection con = DriverManager.getConnection
		         ("jdbc:oracle:thin:@oratest2.lab.bac-lac.gc.ca:1533:mltup","webcan",
		         "webcantest"); */ 
		
		//For production environment use the following connection object
	Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//orapro4.lac-bac.gc.ca:1528/webcp",
				"appwebcan", "web001");
		
		//Connection con = DriverManager.getConnection("appwebcan/web001@orapro4.lac-bac.gc.ca:1528/webcp");
		
		
				
		ResultSet rs = null;
		List <String> crawlJobs = new ArrayList<String>();
		
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {		

				crawlJobs.add(rs.getString(1));
			}
		} catch (SQLException sqle) {
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

	return crawlJobs;
	}
}
