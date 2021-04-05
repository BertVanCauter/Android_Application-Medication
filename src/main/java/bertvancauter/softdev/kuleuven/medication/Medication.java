package bertvancauter.softdev.kuleuven.medication;

import android.icu.util.LocaleData;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Medication implements Parcelable
{
    private String name;
    private int number;
    private boolean push;
    private boolean email;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;


    public Medication(String naam, int aantal, boolean mail, boolean duw, int jaar, int maand, int dag, int uren, int minuten)
    {
        name = naam;
        number = aantal;
        email = mail;
        push = duw;
        year = jaar;
        month = maand;
        day = dag;
        hour = uren;
        minutes = minuten;
    }


    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }


    protected Medication(Parcel in) {
        name = in.readString();
        number = in.readInt();
        push = in.readByte() != 0;
        email = in.readByte() != 0;
        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
        hour = in.readInt();
        minutes = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(number);
        dest.writeByte((byte) (push ? 1 : 0));
        dest.writeByte((byte) (email ? 1 : 0));
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(day);
        dest.writeInt(hour);
        dest.writeInt(minutes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Medication> CREATOR = new Creator<Medication>() {
        @Override
        public Medication createFromParcel(Parcel in) {
            return new Medication(in);
        }

        @Override
        public Medication[] newArray(int size) {
            return new Medication[size];
        }
    };

    public String getName()
    {
        return name;
    }

    public int getNumber()
    {
        return number;
    }


}
