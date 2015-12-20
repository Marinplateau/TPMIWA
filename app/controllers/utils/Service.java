package controllers.utils;

import play.Play;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Service {
    public final static ServiceName SERVICE_NAME = ServiceName.TP_MIWA;
    public final static Integer SERVICE_PORT = (Play.application().isProd()) ? 80 : 9000;

    public static final String MAIN_WS_PATH_CLOCK = "/miwa/api/callback";
    public static final String MAIN_WS_PATH_APP = "/miwa/api/app";

    private static Service service = new Service();

    private Map<ServiceName, String> services;

    private Service() {
        if (Play.isProd())
            loadService(Play.application().getFile("conf/prod.properties"));
        else
            loadService(Play.application().getFile("conf/dev.properties"));
    }

    public static Service getInstances() {
        return service;
    }

    private void loadService(File filename) {
        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            inputStream = new FileInputStream(filename);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        this.services = new HashMap<>();
        for (ServiceName serviceName : ServiceName.values())
            this.services.put(serviceName, properties.getProperty(serviceName.toString()));

        for (Map.Entry<ServiceName, String> entry : services.entrySet())
            System.out.println(entry.getKey() + " : " + entry.getValue());
    }

    public String getServiceURL(ServiceName name) {
        return this.services.get(name);
    }

    public String getServiceHttpURL(ServiceName name) {
        return "http://" + this.services.get(name);
    }
}
