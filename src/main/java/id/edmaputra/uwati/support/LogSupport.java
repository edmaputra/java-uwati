package id.edmaputra.uwati.support;

import javax.servlet.http.HttpServletRequest;

public class LogSupport {
	
	public static String hapus(String user, String entity, HttpServletRequest request){
		String log = "USER : "+user+", ";
		log += "DATA : "+entity+", ";
		log += "OPERASI : HAPUS, ";
		log += "IP ADDRESS : "+request.getRemoteAddr()+", ";
		log += "HOST : "+request.getRemoteHost()+" ";
		return log;
	}
	
	public static String hapusGagal(String user, String entity, HttpServletRequest request){
		String log = "USER : "+user+", ";
		log += "DATA : "+entity+", ";
		log += "OPERASI : GAGAL HAPUS, ";
		log += "IP ADDRESS : "+request.getRemoteAddr()+", ";
		log += "HOST : "+request.getRemoteHost()+" ";
		return log;
	}
	
	public static String tambah(String user, String entity, HttpServletRequest request){
		String log = "USER : "+user+", ";
		log += "DATA : "+entity+", ";
		log += "OPERASI : TAMBAH, ";
		log += "IP ADDRESS : "+request.getRemoteAddr()+", ";
		log += "HOST : "+request.getRemoteHost()+" ";
		return log;
	}
	
	public static String tambahGagal(String user, String entity, HttpServletRequest request){
		String log = "USER : "+user+", ";
		log += "DATA : "+entity+", ";
		log += "OPERASI : GAGAL TAMBAH, ";
		log += "IP ADDRESS : "+request.getRemoteAddr()+", ";
		log += "HOST : "+request.getRemoteHost()+" ";
		return log;
	}
	
	public static String edit(String user, String entity, HttpServletRequest request){
		String log = "USER : "+user+", ";
		log += "DATA : "+entity+", ";
		log += "OPERASI : EDIT, ";
		log += "IP ADDRESS : "+request.getRemoteAddr()+", ";
		log += "HOST : "+request.getRemoteHost()+" ";
		return log;
	}
	
	public static String editGagal(String user, String entity, HttpServletRequest request){
		String log = "USER : "+user+", ";
		log += "DATA : "+entity+", ";
		log += "OPERASI : GAGAL EDIT, ";
		log += "IP ADDRESS : "+request.getRemoteAddr()+", ";
		log += "HOST : "+request.getRemoteHost()+" ";
		return log;
	}
	
	public static String load(String user, HttpServletRequest request){
		String log = "USER : "+user+", ";
		log += "OPERASI : LOAD, ";
		log += "PATH : "+request.getServletPath()+", ";
		log += "IP ADDRESS : "+request.getRemoteAddr()+", ";
		log += "HOST : "+request.getRemoteHost()+" ";
		return log;
	}
	
	public static String loadGagal(String user, HttpServletRequest request){
		String log = "USER : "+user+", ";
		log += "OPERASI : GAGAL LOAD, ";
		log += "PATH : "+request.getServletPath()+", ";
		log += "IP ADDRESS : "+request.getRemoteAddr()+", ";
		log += "HOST : "+request.getRemoteHost()+" ";
		return log;
	}

}
