package live.nerotv.shademebaby.utils;

import live.nerotv.shademebaby.ShadeMeBaby;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static File downloadFile(String urlString, String path) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                File outputFile = new File(path);
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                return outputFile;
            }
        } catch (Exception ignore) {}
        return null;
    }

    public static boolean unzipFile(String filePath, String outputPathString) {
        Path outputPath = Paths.get(outputPathString);
        try (ZipArchiveInputStream zipInput = new ZipArchiveInputStream(new FileInputStream(filePath))) {
            ArchiveEntry entry;
            while ((entry = zipInput.getNextEntry()) != null) {
                Path path = Paths.get(outputPath.toString(), entry.getName());
                if (entry.isDirectory()) {
                    path.toFile().mkdirs();
                } else {
                    try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInput.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    public static void extractResourceFile(String sourceFileName, String destinationFileName, Class class_) {
        new File(new File(destinationFileName).getParent()).mkdirs();
        try {
            ClassLoader classLoader = class_.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(sourceFileName);
            if (inputStream == null) {
                return;
            }
            OutputStream outputStream = new FileOutputStream(destinationFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getResourceFile(String resourceString, Class class_) {
        try {
            return new File(class_.getClassLoader().getResource(resourceString).getFile());
        } catch (Exception e) {
            ShadeMeBaby.getLogger().debug("[FILE UTIL] Couldn't get resources file \""+resourceString+"\": "+e.getMessage());
            return null;
        }
    }
}