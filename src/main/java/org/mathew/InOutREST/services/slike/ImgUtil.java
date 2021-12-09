package org.mathew.InOutREST.services.slike;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImgUtil {

    protected static File kreirajFileRef(MultipartFile file,String folder) throws IOException {
        Path path = Paths.get(folder,file.getOriginalFilename());
        Files.write(path,file.getBytes());
        return path.toFile();
    }

    protected static boolean kompresujSliku(File file){
        try {
            BufferedImage bfImage = ImageIO.read(file);
            BufferedImage kompresovanaSlika = Scalr.resize(bfImage,(int) (bfImage.getWidth()*0.7),(int) (bfImage.getHeight()*0.7));

            Path fileZaBrisanje = Paths.get(file.getPath());
            Files.delete(fileZaBrisanje);

            File novaSlika = fileZaBrisanje.toFile();
            ImageIO.write(kompresovanaSlika,"jpg",novaSlika);
            return true;

        } catch (IOException e) {
            return false;
        }

    }

}
