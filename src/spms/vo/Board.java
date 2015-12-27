package spms.vo;

import java.util.Date;

public class Board {

	protected int no;
	protected String email;
	protected String password;
	protected String content;
	protected Date date;
	
	
	public int getVno(){
		return no;
	}
	
	public Board setVno(int no){
		this.no = no;
		return this;
	}
	
	public String getEmail(){
		return email;
	}
	
	public Board setEmail(String email){
		this.email = email;
		return this;
	}

	public String getPassword(){
		return password;
	}
	
	public Board setPassword(String password){
		this.password = password;
		return this;
	}
	
	public String getContent(){
		return content;
	}
	
	public Board setContent(String content){
		this.content = content;
		return this;
	}
	
	public Date getDate(){
		return date;
	}
	
	public Board setDate(Date date){
		this.date = date;
		return this;
	}

}
