package q6.codejava.swing.download;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
//import java.nio.file.Path;


public class HTTPDownloadUtil {

    private HttpsURLConnection httpConn;

    /**
     * hold input stream of HttpURLConnection
     */
    private InputStream inputStream;

    private String fileName;
    private int contentLength;

    /**
     * Downloads a file from a URL
     *
     * @param fileURL
     *            HTTP URL of the file to be downloaded
     * @throws IOException when file you want to download is not downloadable or does not exist
     */
    public void downloadFile(String fileURL) throws IOException {


        // Path path = Path.of(fileURL) ;

        java.net.URL wsURL = new URL(null, fileURL,new sun.net.www.protocol.https.Handler());
        httpConn = (HttpsURLConnection) wsURL.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1
                );
            }

            // output for debugging purpose only
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            inputStream = httpConn.getInputStream();

        } else {
            throw new IOException(
                    "No file to download. Server replied HTTP code: "
                            + responseCode);
        }
    }

    public void disconnect() throws IOException {
        inputStream.close();
        httpConn.disconnect();
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getContentLength() {
        return this.contentLength;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }
//    public static void main(String [] args){
//        new HTTPDownloadUtil().downloadFile(https://www.pixelstalk.net/wp-content/uploads/2016/07/Wallpapers-pexels-photo.jpg);
//
//    }
}

