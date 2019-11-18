<%@ page import="java.io.File,

                 java.io.InputStream,

                 java.io.FileInputStream,

                 java.io.OutputStream"%>

<%@ page session="false" %>

<%

    String contentType = (String)application.getAttribute("fileupload_type");

    byte[] bytes = (byte[])application.getAttribute("fileupload_bytes");

    if (bytes != null)

    {

        response.setContentType(contentType);

        response.getOutputStream().write(bytes);

    }

%>

