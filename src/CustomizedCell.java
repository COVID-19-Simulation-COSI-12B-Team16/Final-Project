public class CustomizedCell extends Cell{
    CustomizedCell(boolean _isAlive, int _x, int _y, int _lowerBound, int _higherBound){
        super(_isAlive, _x, _y);
        this.lowerBound = _lowerBound;
        this.higherBound = _higherBound;
    }

}
