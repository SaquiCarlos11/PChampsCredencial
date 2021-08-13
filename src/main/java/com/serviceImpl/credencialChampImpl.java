package com.serviceImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.credencialChamp;
import com.repository.IcredencialChampRepository;
import com.serviceInterface.IcredencialChampService;

@Service
public class credencialChampImpl implements Serializable, IcredencialChampService {

	private static final long serialVersionUID = 1L;
	/* Para que traiga el metodo que se va a utilizar */

	@Autowired
	private IcredencialChampRepository cR;

	@Override
	public int insert(credencialChamp cc) {	
		
		cc= cR.save(cc);
		//int rpta = cR.getIDCredencialChamp(credencialChamp.getNamecredencialChamp());


		return cc.getIdcredencialChamp();
	}

	@Override
	public List<credencialChamp> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public void delete(int idcredencialChamp) {
		// TODO Auto-generated method stub
		cR.deleteById(idcredencialChamp);
	}

	@Override
	public Optional<credencialChamp> searchId(int idcredencialChamp) {
		// TODO Auto-generated method stub
		return cR.findById(idcredencialChamp);
	}

	@Override
	public List<credencialChamp> findNameCredencialFull(String namecredencialChamp) {
		// TODO Auto-generated method stub
		return cR.findBynamecredencialChamp(namecredencialChamp);
	}
	
	@Override
	public boolean save(credencialChamp credencialChamp) throws Exception 
	{
		credencialChamp savedChamp =cR.save(credencialChamp);
		credencialChamp =savedChamp;
		return false;
	}

}
