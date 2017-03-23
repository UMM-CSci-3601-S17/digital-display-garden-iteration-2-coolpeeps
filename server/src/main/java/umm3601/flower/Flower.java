package umm3601.flower;

import com.mongodb.util.JSON;
import org.bson.BSON;
import org.bson.Document;

public class Flower {
    String _id;
    String commonName;
    String cultivar;
    String source;
    String gardenLocation;
    int year;

    public boolean readyForJSON()
    {
        return true; //TODO
    }

    public Document toBSONDocument()
    {
        Document out = new Document();
        Document flowerData = new Document();

        flowerData.append("_id", this._id);
        flowerData.append("commonName", this.commonName);
        flowerData.append("cultivar", this.cultivar);
        flowerData.append("source", this.source);
        flowerData.append("gardenLocation", this.gardenLocation);
        flowerData.append("year", this.year);

//        out.append("flower", flowerData);
        return flowerData;
    }

    public String getID() {
        return _id;
    }

    public void setID(String _id) {
        this._id = _id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getCultivar() {
        return cultivar;
    }

    public void setCultivar(String cultivar) {
        this.cultivar = cultivar;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getGardenLocation() {
        return gardenLocation;
    }

    public void setGardenLocation(String gardenLocation) {
        this.gardenLocation = gardenLocation;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}