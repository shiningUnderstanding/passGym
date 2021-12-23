package com.passgym.zzim.vo;

import java.util.List;

import com.passgym.gym.vo.Gym;
import com.passgym.user.vo.User;

public class Zzim {
	
	
	private User user;
	private Gym gym;
	
	
	public Zzim() {}
	
	
	public Zzim(User user, Gym gym) {
		super();
		this.user = user;
		this.gym = gym;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Gym getGym() {
		return gym;
	}
	public void setGym(Gym gym) {
		this.gym = gym;
	}
	@Override
	public String toString() {
		return "Zzim [user=" + user + ", gym=" + gym + "]";
	}
	
	//
	//private List<User> users;
	//private List<Gym> gyms;
	//
	
	 
}
