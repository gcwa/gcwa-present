package ca.gc.collectionscanada.common.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.gc.collectionscanada.domain.SearchParameters;

/**
 * This class holds the context for GCWebArchive portal which is called in GCWebArchiveController
 * and it is totally decoupled with all the classes in this workspace
 * 
 * @author khatrz
 *
 */
public class GCWebArchiveContext {
		
    private static GCWebArchiveContext ctx = null;
    private SearchParameters searchParams;
    private String sectionTitle;
    private String language;
    private String startIndex; 
    
	/**
	 * Default constructor
	 */
	protected GCWebArchiveContext() {
		
	}
	
	/**
	 * Method returns the static instance of GCWebArchiveContext
	 * @return
	 */
	public static GCWebArchiveContext getInstance() {
		if (ctx == null) {
			 ClassPathXmlApplicationContext appCtx = null;
			 try {
				 appCtx = new ClassPathXmlApplicationContext("beans.xml");
				 ctx = (GCWebArchiveContext) appCtx.getBean("gcWebArchCtx");
			 }
			 finally {
				 if(appCtx != null){
					 appCtx.close();	 
				 }				 
			 }			
			return ctx;
		}	
		
		return  ctx;
	}


	/** All setters and getters */
	 public SearchParameters getSearchParams()
	    {
	        return searchParams;
	    }

	    public void setSearchParams(SearchParameters searchParams)
	    {
	        this.searchParams = searchParams;
	    }

	    public String getSectionTitle()
	    {
	        return sectionTitle;
	    }

	    public void setSectionTitle(String sectionTitle)
	    {
	        this.sectionTitle = sectionTitle;
	    }

	    public String getLanguage()
	    {
	        return language;
	    }

	    public void setLanguage(String language)
	    {
	        this.language = language;
	    }

	    public String getStartIndex()
	    {
	        return startIndex;
	    }

	    public void setStartIndex(String startIndex)
	    {
	        this.startIndex = startIndex;
	    }
}
