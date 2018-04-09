package org.wso2.msf4j.example;

public class UsuarioForm2 {
	private String nombre;
	private String sexo;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public UsuarioForm2(String nombre, String sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		System.out.println("ASDF");
		return "UsuarioForm2 [nombre=" + nombre + ", sexo=" + sexo + "]";
	}
	
	
}
