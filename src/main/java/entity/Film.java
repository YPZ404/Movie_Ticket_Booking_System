package entity;

public class Film {

    private int filmID;
    private String filmName;
    private String synopsis;
    private String filmClassification;
    private String releaseDate;
    private String director;
    private String casts;
    private String comingDate;

    public Film (){
        this.filmID = filmID;
        this.filmName = filmName;
        this.synopsis = synopsis;
        this.filmClassification = filmClassification;
        this.releaseDate = releaseDate;
        this.director = director;
        this.casts = casts;
        this.comingDate = comingDate;
    }

    public int getFilmID() {
        return filmID;
    }

    public String getCasts() {
        return casts;
    }

    public String getComingDate() {
        return comingDate;
    }

    public String getDirector() {
        return director;
    }

    public String getFilmClassification() {
        return filmClassification;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public void setComingDate(String comingDate) {
        this.comingDate = comingDate;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setFilmClassification(String filmClassification) {
        this.filmClassification = filmClassification;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

}
