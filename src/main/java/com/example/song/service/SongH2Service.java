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
        Song song = db.queryForObject("SELECT * FROM PLAYLIST WHERE songId=?",new SongRowMapper(),songId);
        return song;
    }
}
