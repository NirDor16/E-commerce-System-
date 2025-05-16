package InbarSarIsrael323101485NirDor212779243;

public class Address {
    private String country;
    private String city;
    private String nameStreet;
    private int numBuilding;

    public Address(String country, String city, String nameStreet, int numBuilding) {
        this.country = country;
        this.city = city;
        this.numBuilding = numBuilding;
        this.nameStreet = nameStreet;
    }

    public Address(Address other) {
        this.country = other.country;
        this.city = other.city;
        this.numBuilding = other.numBuilding;
        this.nameStreet = other.nameStreet;
    }

    public String getCountry() {
        return this.country;
    }

    public boolean setCountry(String country) {
        this.country = country;
        return true;
    }

    public String getCity() {
        return this.city;
    }

    public boolean setCity(String city) {
        this.city = city;
        return true;
    }

    public String getNameStreet() {
        return this.nameStreet;
    }

    public boolean setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
        return true;
    }

    public int getNumBuilding() {
        return this.numBuilding;
    }

    public boolean setNumBuilding(int numBuilding) {
        this.numBuilding = numBuilding;
        return true;
    }

    @Override
    public String toString() {
        return "Address\n" + "country: " + this.country + ", city: " + this.city + ", nameStreet: " + this.nameStreet + ", numBuilding: " + this.numBuilding;
    }
}
