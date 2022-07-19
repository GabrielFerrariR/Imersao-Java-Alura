import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class StickerGenerator {
  public void create(InputStream inputStream, String filename, String title) throws Exception {

    //Leitura da imagem

    BufferedImage original = ImageIO.read(inputStream);

    //cria nova imagem em memória com transparencia e com tamanho novo
    int width = original.getWidth();
    int height = original.getHeight();
    int newHeight = height + 200;
    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    //copia a imagem original para nova imagem em memória
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(original, 0 , 0, null);

    //configurar fonte
    var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    //escrever uma frase nova na imagem
    graphics.drawString(title, 100, newHeight - 100);

    // escrever a nova imagem em um arquivo
    ImageIO.write(newImage, "png", new File("src/figures/" + filename));
    }
}
