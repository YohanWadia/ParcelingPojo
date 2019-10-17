package com.apps.yo.firebaserealtimenestedpojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String Id;
    private String name;
    private int marks;
    private Address address;
    private Telephone telephone;

    public Student(){}//needed to read data

    public Student(String id, String name, int marks) {
        Id = id;
        this.name = name;
        this.marks = marks;
    }

    public Student(Parcel in) {
        Id = in.readString();
        name = in.readString();
        marks = in.readInt();
        this.telephone = in.readParcelable(Telephone.class.getClassLoader());// check note ***
        this.address = in.readParcelable(Address.class.getClassLoader());    // check note ***

    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(name);
        dest.writeInt(marks);
        dest.writeParcelable(telephone,flags);// check note ***
        dest.writeParcelable(address,flags);  // check note ***
    }
}

class Address implements Parcelable{
    private String home;
    private String office;

    public Address(){}

    public Address(String home, String office) {
        this.home = home;
        this.office = office;
    }

    protected Address(Parcel in) {
        home = in.readString();
        office = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(home);
        dest.writeString(office);
    }
}

class Telephone implements Parcelable{
    private String personal;
    private String work;

    public Telephone() { }

    public Telephone(String personal, String work) {
        this.personal = personal;
        this.work = work;
    }

    protected Telephone(Parcel in) {
        personal = in.readString();
        work = in.readString();
    }

    public static final Creator<Telephone> CREATOR = new Creator<Telephone>() {
        @Override
        public Telephone createFromParcel(Parcel in) {
            return new Telephone(in);
        }

        @Override
        public Telephone[] newArray(int size) {
            return new Telephone[size];
        }
    };

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(personal);
        dest.writeString(work);
    }
}
// check note ***
//In the case of a nested object... Parcelable still needs you to put those extra lines.
//They dont get implemented automatically from the IDE implementing the required methods
//I feel its possibly cause these Objects arent in the constructor of the main Object(ie Student over here)