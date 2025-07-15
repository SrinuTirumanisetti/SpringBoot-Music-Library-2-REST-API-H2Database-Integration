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
}
