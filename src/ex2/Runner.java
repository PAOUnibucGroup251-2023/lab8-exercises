package ex2;

import ex2.model.Song;
import ex2.model.Status;
import ex2.repository.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Runner {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        Song song1 = new Song(1, "Feel", "Robbie Williams", "Greatest Hits");
        playlist.addSong(song1);
        Song song2 = new Song(2, "Hello", "Adele", "Greatest Hits 2");
        playlist.addSong(song2);
        Song song3 = new Song(3, "Money", "Pink Floyd", "Greatest Hits 3");

        Queue<Song> playlistSongs = playlist.getSongs();
        List<Song> playedSongs = new ArrayList<>();

        while (!playlistSongs.isEmpty()) {
            Song song = playlistSongs.poll();
            song.setStatus(Status.PLAYING);
            System.out.println(song.getTitle() + " " + song.getStatus());
            song.setStatus(Status.PLAYED);
            System.out.println(song.getTitle() + " " + song.getStatus());
            playedSongs.add(song);
        }

        System.out.println("Your music history:");
        for (Song song : playedSongs) {
            System.out.println(song.getTitle() + " " + song.getStatus());
        }
    }
}
