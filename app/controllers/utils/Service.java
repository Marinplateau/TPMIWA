package controllers.utils;

import play.Play;

import java.util.HashMap;
import java.util.Map;

public class Service {

    private static Service service = null;

    private Integer port;
    private String host;
    private Map<String, String> services;


    public static final String MAIN_WS_PATH_CLOCK = "/miwa/api/callback";
    public static final String MAIN_WS_PATH_APP = "/miwa/api/app";

    public static final String MAIN_WS = "MAIN_WS";
    public static final String FTP = "FTP";
    public static final String BACK_OFFICE = "BACK_OFFICE";
    public static final String BI_SYSTEM = "BI_SYSTEM";
    public static final String BUSINESS_MANAGEMENT = "BUSINESS_MANAGEMENT";
    public static final String CRM = "CRM";
    public static final String E_COMMERCE = "E_COMMERCE";
    public static final String MONETARY_SYSTEM = "MONETARY_SYSTEM";
    public static final String POS_SYSTEM = "POS_SYSTEM";
    public static final String PRODUCT_CATALOG = "PRODUCT_CATALOG";
    public static final String PROVIDER = "PROVIDER";
    public static final String WHAREHOUSE_MANAGEMENT = "WHAREHOUSE_MANAGEMENT";
    public static final String TP_MIWA = "TP_MIWA";

    private Service() {
        if (Play.isProd())
            loadProd();
        else
            loadDev();
    }

    public static Service getInstances() {
        if (service == null)
            service = new Service();

        return service;
    }

    private void loadProd() {
        this.port = Integer.parseInt(System.getenv("MIWA_PORT"));
        this.services = new HashMap<>();
        this.services.put(MAIN_WS, "http://main.miwa.bnf.sigl.epita.fr");
        this.services.put(FTP, "ftp://ftp.miwa.bnf.sigl.epita.fr");
        this.services.put(BACK_OFFICE, "http://back-office.miwa.bnf.sigl.epita.fr");
        this.services.put(BI_SYSTEM, "http://bi-system.miwa.bnf.sigl.epita.fr");
        this.services.put(BUSINESS_MANAGEMENT, "http://business-management.miwa.bnf.sigl.epita.fr");
        this.services.put(CRM, "http://crm.miwa.bnf.sigl.epita.fr");
        this.services.put(E_COMMERCE, "http://e-commerce.miwa.bnf.sigl.epita.fr");
        this.services.put(MONETARY_SYSTEM, "http://monetary-system.miwa.bnf.sigl.epita.fr");
        this.services.put(POS_SYSTEM, "http://pos-system.miwa.bnf.sigl.epita.fr");
        this.services.put(PRODUCT_CATALOG, "http://product-catalogue.miwa.bnf.sigl.epita.fr");
        this.services.put(PROVIDER, "http://provider.miwa.bnf.sigl.epita.fr");
        this.services.put(WHAREHOUSE_MANAGEMENT, "http://wharehouse-management.miwa.bnf.sigl.epita.fr");
    }

    private void loadDev() {
        this.port = 9000;
        this.host = "http://127.0.0.1" + port;
        this.services = new HashMap<>();
        this.services.put(MAIN_WS, "http://127.0.0.1:8181");
        this.services.put(TP_MIWA, "http://127.0.0.1");
    }

    public Integer getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getMAIN_WS() {
        return MAIN_WS;
    }

    public String getMAIN_WS_PATH_CLOCK() {
        return MAIN_WS_PATH_CLOCK;
    }

    public String getMAIN_WS_PATH_APP() {
        return MAIN_WS_PATH_APP;
    }

    public String getFTP() {
        return FTP;
    }

    public String getBACK_OFFICE() {
        return BACK_OFFICE;
    }

    public String getBI_SYSTEM() {
        return BI_SYSTEM;
    }

    public String getBUSINESS_MANAGEMENT() {
        return BUSINESS_MANAGEMENT;
    }

    public String getCRM() {
        return CRM;
    }

    public String getE_COMMERCE() {
        return E_COMMERCE;
    }

    public String getMONETARY_SYSTEM() {
        return MONETARY_SYSTEM;
    }

    public String getPOS_SYSTEM() {
        return POS_SYSTEM;
    }

    public String getPRODUCT_CATALOG() {
        return PRODUCT_CATALOG;
    }

    public String getPROVIDER() {
        return PROVIDER;
    }

    public String getWHAREHOUSE_MANAGEMENT() {
        return WHAREHOUSE_MANAGEMENT;
    }

    public String getTPMIWA() {
        return TP_MIWA;
    }

    public String getServiceURL(String name)
    {
        return this.services.get(name);
    }
}