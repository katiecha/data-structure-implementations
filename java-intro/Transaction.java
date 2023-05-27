package java_intro;

public class Transaction {
    String _fDate;
    String _fTime;
    String _fCategory;
    float _price;
    int _quantity;
    float _rating;
    int _duration;

    Transaction(String date, String time, String category, float price, int quantity, float rating, int duration){
        _fDate = date;
        _fTime = time;
        _fCategory = category;
        _price = price;
        _quantity = quantity;
        _rating = rating;
        _duration = duration;
    }

    public String getDate() {
        return _fDate;
    }

    public String getTime() {
        return _fTime;
    }

    public String getCategory() {
        return _fCategory;
    }

    public float getPrice() {
        return _price;
    }

    public int getQuantity() {
        return _quantity;
    }

    public float getRating() {
        return _rating;
    }

    public int getDuration() {
        return _duration;
    }
}


