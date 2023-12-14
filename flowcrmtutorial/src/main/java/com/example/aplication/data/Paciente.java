package com.example.aplication.data;

public class Paciente {
	
	
	
	private String dni;
	private String nombre;
	private String apellido;
	private String fecha;
	private String genero;
	private String direccion;
	private String telefono;
	private String responsable;
	
	
	
	
	
	public Paciente() {
		
	}




	public String getDni() {
		return dni;
	}




	public void setDni(String dni) {
		this.dni = dni;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getApellido() {
		System.out.println(" apellidos " + apellido);
		return apellido;
	}




	public void setApellido(String apellido) {
		
		this.apellido = apellido;
	}




	public String getFecha() {
		System.out.println(" la fecha establecoida es "+fecha);
		return fecha;
		
	}




	public void setFechaNac(String fechaNac) {
		this.fecha = fechaNac;
		
		
	}




	public String getGenero() {
		return genero;
	}




	public void setGenero(String string) {
		this.genero = string;
	}




	public String getDireccion() {
		return direccion;
	}




	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}




	public String getTelefono() {
		return telefono;
	}




	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public String getResponsable() {
		return responsable;
	}




	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}




	
	
	


}
