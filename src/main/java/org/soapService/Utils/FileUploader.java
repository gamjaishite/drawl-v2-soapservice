package org.soapService.Utils;

import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;

import javax.activation.DataHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUploader {
    private static final int MAX_IMAGE_SIZE = 200_000;
    private static final int MAX_VIDEO_SIZE = 100_000_000;

    public static boolean validateImage(DataHandler dataHandler) {
        try {
            Tika tika = new Tika();
            String mimeTypes = tika.detect(dataHandler.getInputStream());
            int size = IOUtils.toByteArray(dataHandler.getInputStream()).length;

            System.out.println("MIME Types: " + mimeTypes + " " + mimeTypes.matches("^image/(jpg|jpeg|png|webp)$"));
            System.out.println("Size Image: " + size);

            if (!mimeTypes.matches("^image/(jpg|jpeg|png|webp)$") || size > MAX_IMAGE_SIZE) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validateVideo(DataHandler dataHandler) {
        try {
            Tika tika = new Tika();
            String mimeTypes = tika.detect(dataHandler.getInputStream());
            int size = IOUtils.toByteArray(dataHandler.getInputStream()).length;

            System.out.println("MIME Types: " + mimeTypes);
            System.out.println("Size Image: " + size);

            if (!mimeTypes.contains("video") || size > MAX_VIDEO_SIZE) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String upload(DataHandler dataHandler, FileType fileType) throws IOException {
        File file = null;
        String filename = "";
        if (fileType == FileType.IMAGE) {
            filename = "poster_" + UUID.randomUUID() + ".webp";
            file = new File("src/main/resources/posters/" + filename);
        } else {
            filename = "trailer_" + UUID.randomUUID() + ".mp4";
            file = new File("src/main/resources/trailers/" + filename);
        }

        java.nio.file.Files.copy(dataHandler.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        IOUtils.closeQuietly(dataHandler.getInputStream());

        return filename;
    }
}
