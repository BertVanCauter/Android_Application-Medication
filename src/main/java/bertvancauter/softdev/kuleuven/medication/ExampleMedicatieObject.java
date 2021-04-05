package bertvancauter.softdev.kuleuven.medication;

public class ExampleMedicatieObject
{
    private int mImageResource;
    private String mText1;
    private String mText2;
    private int jaar;
    private int maand;
    private int dag;


    public int getJaar() {
        return jaar;
    }

    public int getMaand() {
        return maand;
    }

    public int getDag() {
        return dag;
    }


    public ExampleMedicatieObject(int imageResource, String text1, String text2,int jaar, int maand, int dag)
    {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
        this.jaar = jaar;
        this.maand = maand;
        this.dag = dag;
    }

    public void changeText1 (String text)
    {
    mText1 = text;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

}
