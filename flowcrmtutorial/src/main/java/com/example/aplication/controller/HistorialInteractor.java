package com.example.aplication.controller;

import com.example.aplication.data.Historial;

public interface HistorialInteractor {

	void consultarHistorial(String dni);

	void crearHistorial(Historial historial);
	
	void consultarMedicos();

}
