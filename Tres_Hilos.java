import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TresHilos 
{

    public static void main(String[] args) {
        // Creamos tres hilos
        Thread hilo1 = new Hilo("Hilo 1");
        Thread hilo2 = new Hilo("Hilo 2");
        Thread hilo3 = new HiloAudio("Hilo 3", "Musica.wav");

        // Iniciamos los tres hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }

    // Clase que representa un hilo que imprime un mensaje
    static class Hilo extends Thread {
        private String nombre;

        public Hilo(String nombre) {
            this.nombre = nombre;
        }

        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println(nombre + " - " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Interrupción del hilo " + nombre);
                }
            }
        }
    }

    // Clase que representa un hilo que reproduce un archivo de audio
    static class HiloAudio extends Thread 
    {
        private String nombre;
        private String archivo;

        public HiloAudio(String nombre, String archivo) 
        {
            this.nombre = nombre;
            this.archivo = archivo;
        }

        public void run() {
            try {
                File file = new File(archivo);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (Exception ex) {
                System.out.println("Error al reproducir el audio: " + ex.getMessage());
            }
        }
    }
}