<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%
//byte image=();
OutputStream oImage;
try {
        byte barray[] = request.getParameter("image").getBytes();
        response.setContentType("image/gif");
        oImage=response.getOutputStream();
        oImage.write(barray);
        oImage.flush();
        oImage.close();
}
catch(Exception ex){
    //ex.printStackTrace();
}
%>
