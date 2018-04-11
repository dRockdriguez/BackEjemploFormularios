/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.msf4j.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.rpc.ServiceException;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wso2.msf4j.HttpStreamHandler;
import org.wso2.msf4j.HttpStreamer;

import DefaultNamespace.MainProxy;
import DefaultNamespace.MainServiceLocator;

@Path("/hola")
public class HelloService {
	private static final java.nio.file.Path MOUNT_PATH = Paths.get(".");
	private static String nomb = "";
	
	@OPTIONS
	@Path("/llamadaSOAP1")
	@Produces({ "application/json" })
	@ResponseBody
	public Response optionsSOap1() throws NameNotFoundException, NameNullException {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}
	@GET
	@Path("/llamadaSOAP1")
	@Produces({ "application/json" })
	public Response getSOAP1() throws ServiceException, IOException  {
		JSONObject json = new JSONObject();
		
		json.put("mensaje", "pepito");
		return Response.ok(json.toJSONString())
				.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}
	
	@GET
	@Path("/llamadaSOAP")
	@Produces({ "application/json" })
	public Response getSOAP() throws ServiceException, IOException  {
		MainProxy prox = new MainProxy();
		
		String respuesta = prox.holaMundo();       
        
		return Response.ok(respuesta)
				.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}
	
	
	@OPTIONS
	@Path("/form2")
	@Produces({ "application/json" })
	@ResponseBody
	public Response formDos() throws NameNotFoundException, NameNullException {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}
	
	
	@POST
	@Path("/form2")
	@Produces({ "application/json" })
	@ResponseBody
	public Response postFormDos(@RequestBody UsuarioForm2 usuario) throws NameNotFoundException, NameNullException {
		System.out.println(usuario);
		JSONObject json = new JSONObject();
		String sex = "";
		
		sex = usuario.getSexo().equalsIgnoreCase("m") ? "senhora": "senhor";
		
		if(this.nomb.equals(usuario.getNombre())){
			json.put("mensaje", "Los nombres coinciden " + sex);
		}
		else{
			json.put("mensaje", "Los nombres no coinciden " + sex);
		}

		return Response.ok(json, MediaType.APPLICATION_JSON).build();

		
	}
	
	@OPTIONS
	@Path("/usuario")
	@Produces({ "application/json" })
	@ResponseBody
	public Response optionsUsuario() throws NameNotFoundException, NameNullException {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}
	
	
	@POST
	@Path("/usuario")
	@Produces({ "application/json" })
	@ResponseBody
	public Response postUsuario(@RequestBody Usuario usuario) throws NameNotFoundException, NameNullException {
		System.out.println(usuario);
		
		this.nomb = usuario.getNombre();
		
		if("David".equalsIgnoreCase(usuario.getNombre())){
			JSONObject json = new JSONObject();
			json.put("mensaje", "Hola " + usuario.getNombre() + " " + usuario.getApellido() + " -- Correo: " + usuario.getCorreo());
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}else{
			JSONObject json = new JSONObject();
			json.put("mensaje", "Nombre incorrecto");
			return Response.ok(json, MediaType.APPLICATION_JSON).build();  
		}
		
		
	}
	
	@GET
	@Path("/hola/{prueba}")
	@Produces({ "application/json" })
	public Response getHoliPathParam(@PathParam("prueba") String prueba) throws NameNotFoundException, NameNullException {
		if (prueba == null) {
			throw new NameNullException("El nombre es nulo");
		}
		if (prueba.isEmpty()) {
			throw new NameNotFoundException("El nombre esta vacio");
		}
		return Response.ok(new PruebaBody(prueba))
				.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}
	
	@GET
	@Path("/hola")
	@Produces({ "application/json" })
	public Response getHoli(@QueryParam("nombre") String nombre) throws NameNotFoundException, NameNullException {
		if (nombre == null) {
			throw new NameNullException("El nombre es nulo");
		}
		if (nombre.isEmpty()) {
			throw new NameNotFoundException("El nombre esta vacio");
		}
		String json = "Hola desde el GET del API " + nombre;
		return Response.ok(new PruebaBody(nombre))
				.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}

	@POST
	@Path("/hola")
	@Produces({ "application/json" })
	@ResponseBody
	public Response postHoli(@RequestBody PruebaBody nombre) throws NameNotFoundException, NameNullException {
		System.out.println(nombre);
		
		//String json = "Hola desde el POST del API " + nombre;
		return Response.status(Response.Status.CREATED)
				.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}

	@OPTIONS
	@Path("/hola")
	@Produces({ "application/json" })
	public Response optionsHoli(@QueryParam("nombre") String nombre) {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}

	@GET
	@Path("/holaJSON")
	@Produces({ "application/json" })
	public Response getHoliJSON(@QueryParam("nombre") String nombre) throws NameNotFoundException, NameNullException {
		if (nombre == null) {
			throw new NameNullException("El nombre es nulo");
		}
		if (nombre.isEmpty()) {
			throw new NameNotFoundException("El nombre esta vacio");
		}
		JSONObject json = new JSONObject();
		json.put("saludo", "Hola desde el GET del API " + nombre);

		return Response.ok(json, MediaType.APPLICATION_JSON).status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}

	@POST
	@Path("/holaJSON")
	@Produces({ "application/json" })
	public Response postHoliJSON(@QueryParam("nombre") String nombre) throws NameNotFoundException, NameNullException {
		if (nombre == null) {
			throw new NameNullException("El nombre es nulo");
		}
		if (nombre.isEmpty()) {
			throw new NameNotFoundException("El nombre esta vacio");
		}
		JSONObject json = new JSONObject();
		json.put("saludo", "Hola desde el POST del API " + nombre);

		return Response.ok(json, MediaType.APPLICATION_JSON).status(Response.Status.OK)
				.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}

	@OPTIONS
	@Path("/holaJSON")
	@Produces({ "application/json" })
	public Response optionsHoliJSON(@QueryParam("nombre") String nombre) {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,Authorization,Content-Type, Accept, X-Requested-With")
				.build();
	}

	@POST
	@Path("/subirFichero")
	public void postFile(@Context HttpStreamer httpStreamer, @QueryParam("fileName") String fileName)
			throws IOException {
		httpStreamer.callback(new HttpStreamHandlerImpl(fileName));
	}
	
	
	private static class HttpStreamHandlerImpl implements HttpStreamHandler {
        private FileChannel fileChannel = null;
        private org.wso2.msf4j.Response response;
        
        public HttpStreamHandlerImpl(String fileName) throws FileNotFoundException {
            File file = Paths.get(MOUNT_PATH.toString(), fileName).toFile();
            if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
                fileChannel = new FileOutputStream(file).getChannel();
            }
        }

        @Override
        public void init(org.wso2.msf4j.Response response) {
            this.response = response;
        }

        @Override
        public void end() throws Exception {
            fileChannel.close();
            JSONObject json = new JSONObject();
			json.put("mensaje", "Fichero subido correctamente.");
			
            System.out.println("RESPONDO");
            response.setStatus(Response.Status.ACCEPTED.getStatusCode());
            response.setEntity(json);
            response.send();
        }

        @Override
        public void chunk(ByteBuffer content) throws Exception {
            if (fileChannel == null) {
                throw new IOException("Unable to write file");
            }
            fileChannel.write(content);
        }

        @Override
        public void error(Throwable cause) {
            try {
                if (fileChannel != null) {
                    fileChannel.close();
                }
            } catch (IOException e) {
            }
        }
    }

}
