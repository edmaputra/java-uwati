package id.edmaputra.uwati.service.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.edmaputra.uwati.entity.pengguna.Pengguna;
import id.edmaputra.uwati.entity.pengguna.Role;
import id.edmaputra.uwati.service.pengguna.PenggunaService;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);
	
	@Autowired
	private PenggunaService penggunaService;

	@Override
	public UserDetails loadUserByUsername(String nama) throws UsernameNotFoundException {
		logger.info("USER: "+nama+" MENCOBA LOGIN");
		Pengguna pengguna = penggunaService.temukan(nama);		
		if (pengguna == null){
			logger.info("USER: "+nama+" TIDAK DITEMUKAN");
			throw new UsernameNotFoundException("Username Tidak Ditemukan");
		}
		return new org.springframework.security.core.userdetails.User(pengguna.getNama(), pengguna.getKataSandi(),  true, true, true, true, getGrantedAuthorities(pengguna));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Pengguna pengguna){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(Role roles : pengguna.getRoles()){
            authorities.add(new SimpleGrantedAuthority(roles.getNama()));
        }
        return authorities;
    }
	
	

}
