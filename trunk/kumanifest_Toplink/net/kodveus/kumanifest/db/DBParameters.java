package net.kodveus.kumanifest.db;

public class DBParameters{
	String dbURL;
	String dbUser;
	String dbPassword;
	String dbDriver;
	
	public String toString(){
		return dbURL+" "+dbUser+" "+dbPassword+" "+dbDriver;
	}
}