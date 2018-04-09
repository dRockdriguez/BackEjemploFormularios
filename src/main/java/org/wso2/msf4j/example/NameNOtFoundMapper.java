package org.wso2.msf4j.example;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class NameNOtFoundMapper implements ExceptionMapper<NameNotFoundException>{
	public Response toResponse(NameNotFoundException ex) {
        return Response.status(404).
                entity(ex.getMessage()).
                type("text/plain").
                build();
    }
}
