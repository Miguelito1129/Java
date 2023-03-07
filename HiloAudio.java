import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class HiloAudio extends Thread {
    private String nombreArchivo;
    
    public HiloAudio(String nombreArchivo) 
    {
        this.nombreArchivo = nombreArchivo;
    }
    
    @Override
    public void run() {
        try {
            File archivo = new File(nombreArchivo);
            AudioInputStream ais = AudioSystem.getAudioInputStream(archivo);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            System.err.println("Error al reproducir el archivo de audio: " + e.getMessage());
        }
    }
}