/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlistgen.Model;

/**
 *
 * @author nami
 */
public class Song {
    private String _title;
    private String _artist;
    private String _URL;
    private int _nr;
    
    public Song(int nr, String title, String artist, String URL){
        _nr = nr;
        _title = title;
        _artist = artist;
        _URL = URL;
    }

    /**
     * @return the _title
     */
    public String getTitle() {
        return _title;
    }

    /**
     * @param _title the _title to set
     */
    public void setTitle(String _title) {
        this._title = _title;
    }

    /**
     * @return the _artist
     */
    public String getArtist() {
        return _artist;
    }

    /**
     * @param _artist the _artist to set
     */
    public void setArtist(String _artist) {
        this._artist = _artist;
    }

    /**
     * @return the _URL
     */
    public String getURL() {
        return _URL;
    }

    /**
     * @param _URL the _URL to set
     */
    public void setURL(String _URL) {
        this._URL = _URL;
    }

    /**
     * @return the _nr
     */
    public int getNr() {
        return _nr;
    }

    /**
     * @param _nr the _nr to set
     */
    public void setNr(int _nr) {
        this._nr = _nr;
    }
}
