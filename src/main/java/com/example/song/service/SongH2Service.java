/*
 * 
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.song.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.song.model.Song;
import com.example.song.repository.SongRepository;
import com.example.song.model.SongRowMapper;

@Service
public class SongH2Service implements SongRepository{
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Song> getSongs(){
        Collection<Song> songList = db.query("SELECT * FROM PLAYLIST",new SongRowMapper());
        return new ArrayList<>(songList);
    }

    @Override
    public Song addSong(Song song){
        db.update("INSERT INTO PLAYLIST(songName,lyricist,singer,musicDirector) Values(?,?,?,?)", song.getSongName(),song.getLyricist(),song.getSinger(),song.getMusicDirector());
        Song addedSong = db.queryForObject("SELECT * FROM PLAYLIST WHERE songName=? AND singer=?",new SongRowMapper(),song.getSongName(),song.getSinger());
        return addedSong;
    }

    @Override
    public Song getSongById(int songId){
        try{
            Song song = db.queryForObject("SELECT * FROM PLAYLIST WHERE songId=?",new SongRowMapper(),songId);
            return song;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       
    }

    @Override
    public Song updateSong(int songId, Song song) {
        if (song.getSongName() != null) {
            db.update("UPDATE PLAYLIST SET songName=? WHERE songId=?", song.getSongName(), songId);
        }
        if (song.getLyricist() != null) {
            db.update("UPDATE PLAYLIST SET lyricist=? WHERE songId=?", song.getLyricist(), songId);
        }
        if (song.getSinger() != null) {
            db.update("UPDATE PLAYLIST SET singer=? WHERE songId=?", song.getSinger(), songId);
        }
        if (song.getMusicDirector() != null) {
            db.update("UPDATE PLAYLIST SET musicDirector=? WHERE songId=?", song.getMusicDirector(), songId);
        }
        return getSongById(songId);
    }

    @Override
    public void deleteSong(int songId){
        db.update("DELETE FROM PLAYLIST WHERE songId=?",songId);
    }
}
