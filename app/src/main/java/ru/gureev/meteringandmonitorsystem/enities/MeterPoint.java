package ru.gureev.meteringandmonitorsystem.enities;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.UUID;

@IgnoreExtraProperties
public class MeterPoint implements Serializable {

    private String id = "";
    private String pointNumber = "";
    private String userId = "";
    private String city = "";
    private String district = "";
    private String street = "";
    private String house = "";
    private String flat = "";
    private String description = "";
    private String deviceStatus = "нет данных";
    private String numMeter = "";
    private boolean isProblemPoint = false;
    private boolean isReadingDone = false;
    private String problemDescription = "";
    private String meterReading = "";
    private String imageUrl1 = "";
    private String imageUrl2 = "";
    private String imageUrl3 = "";
    private String dateMetering = "";

    public MeterPoint() {
    }

    public void generateId() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPointNumber() {
        return pointNumber;
    }

    public void setPointNumber(String pointNumber) {
        this.pointNumber = pointNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public boolean isProblemPoint() {
        return isProblemPoint;
    }

    public void setProblemPoint(boolean problemPoint) {
        isProblemPoint = problemPoint;
    }

    public boolean isReadingDone() {
        return isReadingDone;
    }

    public void setReadingDone(boolean readingDone) {
        isReadingDone = readingDone;
    }

    public String getProblemDescription() {
        if (problemDescription.isEmpty()) {
            problemDescription = "нет";
        }
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(String meterReading) {
        this.meterReading = meterReading;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getDateMetering() {
        return dateMetering;
    }

    public void setDateMetering(String dateMetering) {
        this.dateMetering = dateMetering;
    }

    public String getNumMeter() {
        return numMeter;
    }

    public void setNumMeter(String numMeter) {
        this.numMeter = numMeter;
    }

    @Override
    public String toString() {
        return String.format("# %s, %s, %s, %s, %s, %s", pointNumber, city, district, street, house, flat);
    }
}
