package com.example.aplication.model;

import java.io.IOException;

import com.example.aplication.data.Historial;
import com.example.aplication.data.HistorialResponse;
import com.example.aplication.data.Medico;
import com.example.aplication.data.MedicoResponse;
import com.example.aplication.data.Paciente;
import com.example.aplication.data.PacientesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class DatabaseRepositoryImpl {
	
	private static DatabaseRepositoryImpl INSTANCE;
	private static DatabaseClient client;
	
	private DatabaseRepositoryImpl(String url, Long timeout) {
		client=new DatabaseClient(url, timeout);
	}
	
	public static DatabaseRepositoryImpl getInstance(String url, Long timeout) {
		if(INSTANCE==null) {
			synchronized (DatabaseRepositoryImpl.class) {

					INSTANCE=new DatabaseRepositoryImpl(url, timeout);

			}
		}
		return INSTANCE;
	}
	
	public PacientesResponse consultarPacientes() throws IOException {
		Call<PacientesResponse> call=client.getDatabase().consultarPacientes();
		Response<PacientesResponse> response=call.execute();
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	public boolean crearPacientes(Paciente nuevo) throws IOException {
		Call<ResponseBody> call=client.getDatabase().crearPacientes(nuevo);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
	}
	
	

	public boolean actualizarPacientes(Paciente existente) throws IOException {
		Call<ResponseBody> call=client.getDatabase().actualizarPacientes(existente);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
	}
	
	
	public boolean eliminarPacientes(String dni) throws IOException {
		Call<ResponseBody> call=client.getDatabase().eliminarPacientes(dni);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
	}
	
	
	

	public HistorialResponse consultarHistorial(String dni) throws IOException {
	    Call<HistorialResponse> call = client.getDatabase().consultarHistorial(dni);
	    Response<HistorialResponse> response = call.execute();
	    if (response.isSuccessful()) {
	        return response.body();
	    } else {
	        return null;
	    }
	}


	public boolean salvarHistorial(Historial historial) throws IOException {
		Call<ResponseBody> call=client.getDatabase().crearHistorial(historial);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
		
	}
	
	
	
	
	
	
	
	

	public MedicoResponse consultarMedico() throws IOException {
		Call<MedicoResponse> call=client.getDatabase().consultarMedico();
		Response<MedicoResponse> response=call.execute();
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}

	public boolean crearMedico(Medico nuevo) throws IOException {
		Call<ResponseBody> call=client.getDatabase().crearMedico(nuevo);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
	}
	

	public boolean actualizarMedicos(Medico existente) throws IOException {
		Call<ResponseBody> call=client.getDatabase().actualizarMedicos(existente);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
	}
	
	public boolean eliminarMedicos(String carnet) throws IOException {
		Call<ResponseBody> call=client.getDatabase().eliminarMedicos(carnet);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
	}
	
	
	
	
}
