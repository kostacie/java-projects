package com.kostacie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {
    private List<Song> songs;
    private String name;
    private String artist;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public Album() {}

    public boolean addSong(String title, double duration){
        if (searchSong(title) == null){
            songs.add(new Song(title, duration));
//            System.out.println(title + " successfully added to the list.");
            return true;
        }
        else {
//            System.out.println(title + " already exist int the list.");
            return false;
        }

    }

    public Song searchSong(String title){
        for (var song: songs) {
            if(song.getTitle().equals(title))
                return song;
        }
        return null;
    }

    public boolean addToPlaylist(int number, LinkedList<Song> playlist){
        int index = number - 1;
        if (index > 0 && index <= songs.size()) {
            playlist.add(songs.get(index));
            return true;
        }
//        System.out.println("There is no song with such track number in the album.");
        return false;
    }

    public boolean addToPlaylist(String title, LinkedList<Song> playlist) {
        for (var song : songs) {
            if (song.getTitle().equals(title)) {
                playlist.add(song);
                return true;
            }
        }
//        System.out.println("There is no song with such name in the album.");
        return false;
    }
}

