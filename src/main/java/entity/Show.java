package entity;


public class Show {
    private String showID;
    private String filmName;
    private String screensize;
    private String location;
    private String type;
    private String time;
    private int front;
    private int middle;
    private int rear;
    private float price;
    private String director;
    private String casts;
    private String film_synopsis;

    public Show(){};

    public void setFront(int front) {
        this.front = front;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmName() {
        return filmName;
    }

    public int getFront() {
        return front;
    }

    public int getMiddle() {
        return middle;
    }

    public float getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getShowID() {
        return showID;
    }

    public String getTime() {
        return time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMiddle(int middle) {
        this.middle = middle;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setShowID(String showID) {
        this.showID = showID;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setScreensize(String screensize) {
        this.screensize = screensize;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScreensize() {
        return screensize;
    }

    public String getType() {
        return type;
    }

    public int getRear() {
        return rear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getFilm_synopsis() {
        return film_synopsis;
    }

    public String getCasts() {
        return casts;
    }

    public void setFilm_synopsis(String film_synopsis) {
        this.film_synopsis = film_synopsis;
    }
}
