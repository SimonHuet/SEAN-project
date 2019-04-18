package fr.epsi.seanProject.beans;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import fr.epsi.seanProject.listener.StartupListener;
import fr.epsi.seanProject.mbeans.LogLevelMXBean;

public class LogLevel implements LogLevelMXBean {
    private static final Logger Logger = LogManager.getLogger(StartupListener.class);

	@Override
	public String getlevel() {
		// TODO Auto-generated method stub
		return Logger.getLevel().name();
	}

	@Override
	public void setLevel(String valeur) {
		// TODO Auto-generated method stub
		Configurator.setLevel("fr.epsi.seanProject", Level.toLevel(valeur, Level.ERROR));
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return "LogLevel";
	}

	@Override
	public void SetLevelDebug() {
		// TODO Auto-generated method stub
		Configurator.setLevel("fr.epsi.seanProject", Level.DEBUG);
		
	}

	@Override
	public void SetLevelError() {
		// TODO Auto-generated method stub
		Configurator.setLevel("fr.epsi.seanProject", Level.ERROR);
		
	}

	@Override
	public void SetLevelInfo() {
		// TODO Auto-generated method stub
		Configurator.setLevel("fr.epsi.seanProject", Level.INFO);
		
	}
	

}
