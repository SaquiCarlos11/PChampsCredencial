package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="credencialChamp")
public class credencialChamp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcredencialChamp;
	
		
	@Column(name = "namecredencialChamp", nullable = false, length = 45)
	private String namecredencialChamp;
	

	private String foto;

	@Column(name = "dniChamp", nullable = false, length = 45)
	private int dniChamp;

	public credencialChamp() {
		super();
		// TODO Auto-generated constructor stub
	}


	public credencialChamp(int idcredencialChamp, String namecredencialChamp, String foto,int dniChamp) {
		super();
		this.idcredencialChamp = idcredencialChamp;
		this.namecredencialChamp = namecredencialChamp;
		this.foto = foto;
		this.dniChamp = dniChamp;

	}


	public int getDniChamp() {
		return dniChamp;
	}


	public void setDniChamp(int dniChamp) {
		this.dniChamp = dniChamp;
	}


	public int getIdcredencialChamp() {
		return idcredencialChamp;
	}


	public void setIdcredencialChamp(int idcredencialChamp) {
		this.idcredencialChamp = idcredencialChamp;
	}


	public String getNamecredencialChamp() {
		return namecredencialChamp;
	}


	public void setNamecredencialChamp(String namecredencialChamp) {
		this.namecredencialChamp = namecredencialChamp;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}
	

}
