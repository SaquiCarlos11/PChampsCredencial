package com.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.entity.credencialChamp;



public interface IcredencialChampService {
	public int insert(credencialChamp credencialChamp);

	List<credencialChamp> list();

	public void delete(int idcredencialChamp);// Eliminar

	Optional<credencialChamp> searchId(int idcredencialChamp);// Modificar

	List<credencialChamp> findNameCredencialFull(String namecredencialChamp);// Buscar
	
	boolean save(credencialChamp credencialChamp) throws Exception;
	
}
