// Write your code here
package com.example.song.repository;

import com.example.song.model.Song;
import java.util.ArrayList;

public interface SongRepository{
    public ArrayList<Song> getSongs();
    public Song addSong(Song song);
    public Song getSongById(int songId);
}