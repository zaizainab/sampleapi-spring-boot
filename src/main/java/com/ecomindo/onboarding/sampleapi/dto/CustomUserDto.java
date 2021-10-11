package com.ecomindo.onboarding.sampleapi.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDto extends User implements Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fullname;
	private String token;

	public CustomUserDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUserDto(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String nickName) {
		super(username, password, authorities);
		this.fullname = nickName;
	}

	public String getNickName() {
		return fullname;
	}

	public void setNickName(String nickName) {
		this.fullname = nickName;
	}	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}