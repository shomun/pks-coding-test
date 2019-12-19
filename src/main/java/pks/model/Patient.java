package pks.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Patient {

    private int id;

    private int age;

    private char gender;

    private TreeMap<LocalDate, Episode> episodes;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Map<LocalDate, Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(TreeMap<LocalDate, Episode> episodes) {
        this.episodes = episodes;
    }

    public void addEpisodes(Episode episode) {
        if(this.episodes == null){
            this.episodes = new TreeMap<>();
        }
        this.episodes.put(episode.getDate(),episode);
    }

}
