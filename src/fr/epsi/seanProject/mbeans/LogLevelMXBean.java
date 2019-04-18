package fr.epsi.seanProject.mbeans;

public interface LogLevelMXBean  {


	    public String getNom();

	    public String getlevel();
	    public void setLevel(String valeur);
	    
	    public void SetLevelDebug();
	    public void SetLevelError();
	    public void SetLevelInfo();
	    
}
