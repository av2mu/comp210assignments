package assn02;

public class Product {
    String _date;
    String _assembleTime;
    String _category;
    float _fee;
    int _quantity;
    float _time;
    float _cost;

    public String get_date(){
        return _date;
    }
    public void  set_date(String dateIn){
        this._date = dateIn;
    }
    public String get_assembleTime(){
        return _assembleTime;
    }
    public void  set_assembleTime(String assembleTimeIn){
        this._assembleTime = assembleTimeIn;
    }
    public String get_category(){
        return _category;
    }
    public void  set_category(String categoryIn){
        this._category = categoryIn;
    }
    public float get_fee(){
        return _fee;
    }
    public void  set_fee(float feeIn){
        this._fee = feeIn;
    }
    public int get_quantity(){
        return _quantity;
    }
    public void  set_quantity(int quantityIn){
        this._quantity = quantityIn;
    }
    public float get_time(){
        return _time;
    }
    public void  set_time(float timeIn){
        this._time = timeIn;
    }
    public float get_cost(){
        return _cost;
    }
    public void  set_cost(float costIn){
        this._cost = costIn;
    }

}
