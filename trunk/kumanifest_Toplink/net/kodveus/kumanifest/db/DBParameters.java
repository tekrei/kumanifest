package net.kodveus.kumanifest.db;

public class DBParameters{
	String dbURL;
	String dbUser;
	String dbPassword;
	String dbDriver;
	int maxConnection = 5;
	
	public String toString(){
		return dbURL+" "+dbUser+" "+dbPassword+" "+dbDriver+" "+maxConnection;
	}
}