package com.passgym.star.service;

import com.passgym.exception.AddException;
import com.passgym.star.dao.StarDAOOracle;
import com.passgym.star.vo.Star;

public class StarService {
	private StarDAOOracle dao = StarDAOOracle.getInstance();
	
	private static StarService service = new StarService();
	
	private StarService() {}
	
	public static StarService getInstance() {
		return service;
	}
	
	public void addStar(Star star) throws AddException {
		dao.addStar(star);
	}
}
