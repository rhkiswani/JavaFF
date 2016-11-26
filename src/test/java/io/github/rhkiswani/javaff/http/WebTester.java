package io.github.rhkiswani.javaff.http;

import io.github.rhkiswani.javaff.json.JsonHandlerFactory;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.After;
import org.junit.Before;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.BindException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class WebTester {
    protected static final String BASE_URL = "http://localhost:9999/testHttp";

    protected Server server = new Server(9999);
    protected ServerConnector connector = null;

    @Before
    public void startJetty() throws Exception {
        // Create Server
        server.setHandler(new MyServlet());
        // Start Server
        try {
            server.start();
        }catch (BindException b){
            if (b.getMessage().contains("Address already in use")){
                server.stop();
                throw b ;
            }
        }
    }

    @After
    public void stopJetty() {
        try {
            server.stop();
        }
        catch (Exception e) {
            throw new IllegalParamException(e.getMessage());
        }
    }

    private class MyServlet extends AbstractHandler {

        @Override
        public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
            httpServletResponse.setContentType(request.getContentType());
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            sendWhatReceived(httpServletRequest, httpServletResponse);
            request.setHandled(true);
        }

        private void sendWhatReceived(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            Response r = new Response();
            r.method = req.getMethod();
            r.contentType = req.getContentType();
            for (String o : req.getParameterMap().keySet()) {
                r.params.put(o, req.getParameter(o));
            }
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement();
                r.requestHeaders.put(headerName, req.getHeader(headerName));
            }
            if (r.contentType.equalsIgnoreCase("application/json")){
                BufferedReader br = req.getReader();
                String str;
                while( (str = br.readLine()) != null ){
                    r.jsonParams += str;
                }
            }
            resp.getWriter().println(JsonHandlerFactory.getJsonHandler(WebTester.class).toJson(r));
        }
    }

    protected class Response {
        String method;
        String contentType;
        Map<String, String> params = new HashMap<>();
        Map<String, String> requestHeaders = new HashMap<>();
        public String jsonParams = "";
    }
}
