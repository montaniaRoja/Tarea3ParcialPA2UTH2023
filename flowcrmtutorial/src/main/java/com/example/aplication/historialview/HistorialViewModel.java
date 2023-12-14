package com.example.aplication.historialview;

import java.util.List;

import com.example.aplication.data.Historial;
import com.example.aplication.data.Medico;

public interface HistorialViewModel {

	void mostrarHistorialEnGrid(List<Historial> items);

	void mostrarMensaje(String string);

	void llenarComboBoxMedicos(List<Medico> items);

}
