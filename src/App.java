
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import dominio.Album;
import dominio.Artista;
import dominio.Musica;

public class App {
    public static void main(String[] args) throws Exception {

        Musica musica1 = new Musica();
        musica1.setNome("Californication");
        musica1.setDuracao(120);
        musica1.setArquivoAudio("assets/Red-Hot-Chili-Peppers-Californication.wav");
        musica1.setGenero("Rock");

        Musica musica2 = new Musica();
        musica2.setNome("Otherside");
        musica2.setDuracao(120);
        musica2.setArquivoAudio("assets/Red-Hot-Chili-Peppers-Otherside.wav");
        musica2.setGenero("Rock");

        Album album1 = new Album();
        album1.setNome("Primeiro album");
        album1.setAno(2000);
        album1.addMusica(musica1);
        album1.addMusica(musica2);

        Artista redHot = new Artista();
        redHot.setNome("Red Hot Chili Peppers");
        redHot.addAlbum(album1);

        System.out.println("Abrindo o PlayMusic");
        redHot.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio();

        AudioPlayer player = new AudioPlayer();
        int[] index = {0};
        Musica[] playlist = redHot.getAlbuns().get(0).getMusicas().toArray(new Musica [0]);


        // Cria o botão Play/Stop e configura sua ação
        JButton playStopButton = new JButton("Play");
        playStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player.isPlaying) {
                    player.loadAudio(playlist[index[0]].getArquivoAudio());
                    player.playAudio();
                    playStopButton.setText("Stop");
                } else {
                    player.stopAudio();
                    playStopButton.setText("Play");
                }
            }
        });

        JButton nextButton = new JButton("next");
        nextButton.addActionListener(e -> {
            if(index[0] < playlist.length - 1){
                index[0]++;
                player.stopAudio();
                player.loadAudio(playlist[index[0]].getArquivoAudio());
                player.playAudio();
                playStopButton.setText("Stop");
            }
            else{
                System.out.println("Acabaram as músicas");
            }
            });

        JButton beforeButton = new JButton("before");
        beforeButton.addActionListener(e -> {
            if(index[0] > 0){
                index[0]--;
                player.stopAudio();
                player.loadAudio(playlist[index[0]].getArquivoAudio());
                player.playAudio();
                playStopButton.setText("Stop");
            }
            else{
                System.out.println("Acabaram as músicas");
            }
        });

        ImageIcon icon = new ImageIcon("./assets/music.png");
        // Exibe um JOptionPane com o botão Play/Stop
        JOptionPane.showOptionDialog(
                null,
                redHot.getAlbuns().get(0).getMusicas().get(0).getNome(),
                "PlayMusic",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                new Object[] { playStopButton, nextButton, beforeButton }, playStopButton);

        


        // Fecha o clip de áudio ao encerrar o programa
        if (player.audioClip != null) {
            player.audioClip.close();
        }

    }
}
