package controllers.utils;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class FtpSender {

    private static FTPClient connectToServer() throws IOException {

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(Service.getInstances().getServiceURL(ServiceName.FTP), 21);
        ftpClient.login("sigl", "sigl2016");
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        ftpClient.changeWorkingDirectory("/" + Service.SERVICE_NAME);
        if (ftpClient.getReplyCode() == 550)
            ftpClient.makeDirectory("/" + Service.SERVICE_NAME);

        return ftpClient;
    }

    private static void closeConnection(FTPClient ftpClient) throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    public static boolean sendFile(File inputFile) throws IOException {
        FTPClient ftpClient = null;
        boolean done = false;
        try {
            ftpClient = connectToServer();
            ftpClient.changeWorkingDirectory("/" + Service.SERVICE_NAME);
            InputStream inputStream = new FileInputStream(inputFile);
            done = ftpClient.storeFile(inputFile.getName(), inputStream);
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
            outputStream.close();
        }
        finally {
            closeConnection(ftpClient);
        }
        return done;
    }
}

