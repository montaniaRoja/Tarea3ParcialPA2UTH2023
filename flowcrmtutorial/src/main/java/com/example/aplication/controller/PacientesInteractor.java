package com.example.aplication.controller;

import com.example.aplication.data.Paciente;

public interface PacientesInteractor {
	
	
	
	void consultarPacientes();
	void crearPacientes(Paciente nuevo);
	void actualizarPacientes(Paciente existente);
	void eliminarPacientes(String dni);
	

}
