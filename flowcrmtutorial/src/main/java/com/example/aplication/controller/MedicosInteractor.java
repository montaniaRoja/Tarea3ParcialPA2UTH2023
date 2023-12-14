package com.example.aplication.controller;

import com.example.aplication.data.Medico;


public interface MedicosInteractor {

	void consultarMedicos();
	void crearMedico(Medico nuevo);
	void actualizarMedicos(Medico existente);
	void eliminarMedicos(String carnet);
	
	

}
