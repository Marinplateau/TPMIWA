package controllers.utils;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpSender {

    private FTPClient connectToServer() throws IOException {

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(Service.getInstances().getServiceURL(Service.FTP));
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        return ftpClient;
    }

    private void closeConnection(FTPClient ftpClient) throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    public boolean sendFile(File file) throws IOException {
        FTPClient ftpClient = null;
        boolean done = false;
        try {
            ftpClient = connectToServer();
            InputStream inputStream = new FileInputStream(file);
            done = ftpClient.storeFile(file.getName(), inputStream);
            inputStream.close();
        } finally {
            closeConnection(ftpClient);
        }
        return done;
    }

    public File downloadFile() {

    }
}

