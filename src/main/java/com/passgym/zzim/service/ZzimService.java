package com.passgym.zzim.service;

import com.passgym.exception.AddException;
import com.passgym.exception.RemoveException;
import com.passgym.zzim.dao.ZzimDAOOracle;
import com.passgym.zzim.vo.Zzim;

public class ZzimService {
	ZzimDAOOracle dao = ZzimDAOOracle.getInstance();
	
	private static ZzimService zzimService = new ZzimService();
	private ZzimService() {}
	public static ZzimService getInstance() {
		return zzimService;
	}
	
	public void addZzim(Zzim zzim) throws AddException {
		dao.addZzim(zzim);
	}
	
	public void removeZzim(Zzim zzim) throws RemoveException {
		dao.removeZzim(zzim);
	}
}
