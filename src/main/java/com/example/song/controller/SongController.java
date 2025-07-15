/*
 * 
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.song.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.song.service.SongH2Service;
import com.example.song.model.Song;

@RestController
public class SongController{

    @Autowired
    public SongH2Service service;

    @GetMapping("/songs")
    public ArrayList<Song> getSongs(){
        return service.getSongs();
    }
    
    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song){
        return service.addSong(song);
    }

    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable("songId") int songId){
        return service.getSongById(songId);
    }
}