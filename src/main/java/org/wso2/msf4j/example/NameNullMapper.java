package org.wso2.msf4j.example;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NameNullMapper implements ExceptionMapper<NameNullException>{
	public Response toResponse(NameNullException ex) {
        return Response.status(404).
                entity(ex.getMessage()).
                type("text/plain").
                build();
    }
}
