package controllers.utils.sender;


import controllers.utils.Service;
import controllers.utils.ServiceName;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import play.Logger;

import java.io.*;

public class FtpSender {


    private static final String FTP_USER = "sigl";
    private static final String FTP_PASSWORD = "sigl2016";
    private static final String FTP_APP_DIRECTORY = "/" + Service.SERVICE_NAME;

    private static FTPClient connectToServer() throws IOException {

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(Service.getInstances().getServiceURL(ServiceName.FTP), 21);
        ftpClient.login(FTP_USER, FTP_PASSWORD);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        ftpClient.changeWorkingDirectory(FTP_APP_DIRECTORY);
        if (ftpClient.getReplyCode() == 550)
            ftpClient.makeDirectory(FTP_APP_DIRECTORY);

        Logger.info("Connected to FTP server");
        return ftpClient;
    }

    private static void closeConnection(FTPClient ftpClient) throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
        Logger.info("Disconnected to FTP server");

    }

    public static boolean sendFile(File inputFile) throws IOException {
        FTPClient ftpClient = null;
        boolean done = false;
        try {
            ftpClient = connectToServer();
            ftpClient.changeWorkingDirectory(FTP_APP_DIRECTORY);
            InputStream inputStream = new FileInputStream(inputFile);
            done = ftpClient.storeFile(inputFile.getName(), inputStream);
            Logger.info("store File {} on FTP server", inputFile.getName());
            inputStream.close();
        } finally {
            closeConnection(ftpClient);
        }
        return done;
    }

    public static boolean downloadFile(String remoteFile, File outputFile) throws IOException {
        FTPClient ftpClient = null;
        boolean done = false;
        try {
            ftpClient = connectToServer();
            OutputStream outputStream = new FileOutputStream(outputFile);
            done = ftpClient.retrieveFile(remoteFile, outputStream);
            Logger.info("file {} downloaded and store {}", remoteFile, outputFile.getPath());
            outputStream.close();
        }
        finally {
            closeConnection(ftpClient);
        }
        return done;
    }
}

