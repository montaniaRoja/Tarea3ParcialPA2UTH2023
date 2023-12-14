package com.example.aplication.model;

import com.example.aplication.data.Historial;
import com.example.aplication.data.HistorialResponse;
import com.example.aplication.data.Medico;
import com.example.aplication.data.MedicoResponse;
import com.example.aplication.data.Paciente;
import com.example.aplication.data.PacientesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface DatabaseRepository {
	
	
	
	
	//********Pacientes
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/adolfouth/hospital/pacientes")
	Call<PacientesResponse> consultarPacientes();
	
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/adolfouth/hospital/pacientes")
	Call<ResponseBody> crearPacientes(@Body Paciente nuevo);


	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/adolfouth/hospital/pacientes")
	Call<ResponseBody> actualizarPacientes(@Body Paciente existente);
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/adolfouth/hospital/pacientes")
	Call<ResponseBody> eliminarPacientes(@Query("dni") String dni);
	
	
	
	
	
	
	
	//*******************Historial
	
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/adolfouth/hospital/historial")
	Call<HistorialResponse> consultarHistorial(@Query("dni") String dni);


	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/adolfouth/hospital/historial")
	Call<ResponseBody> crearHistorial(@Body Historial historial);
	
	
	
	
	
	
	//******************Medicos
	
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/adolfouth/hospital/medicos")
	Call<ResponseBody> crearMedico(@Body Medico nuevo);
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/adolfouth/hospital/medicos")
	Call<MedicoResponse> consultarMedico();
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/adolfouth/hospital/medicos")
	Call<ResponseBody> actualizarMedicos(@Body Medico existente);
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/adolfouth/hospital/medicos")
	Call<ResponseBody> eliminarMedicos(@Query("carnet") String carnet);
	
	

}
