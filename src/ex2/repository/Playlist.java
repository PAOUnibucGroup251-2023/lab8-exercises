package ex2.repository;

import ex2.model.Song;

import java.util.LinkedList;
import java.util.Queue;

public class Playlist {
    private Queue<Song> songs;

    public Playlist() {
        songs = new LinkedList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public Queue<Song> getSongs() {
        Queue<Song> copy = new LinkedList<>(songs);
        return copy;
    }
}
