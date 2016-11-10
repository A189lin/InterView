package ready.mumu.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mumu  on 2016/11/4
 * Function:
 * Modify by mumu on 2016/11/4
 * Modify Reason:
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 */
public class myParcelable implements Parcelable{

    String name;
    int age;
    String sex;


    //参数是一个Parcel,用它来存储与传输数据
    protected myParcelable(Parcel in) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        name = in.readString();
        age = in.readInt();
        sex = in.readString();
    }

    public static final Creator<myParcelable> CREATOR = new Creator<myParcelable>() {
        @Override
        public myParcelable createFromParcel(Parcel in) {
            return new myParcelable(in);
        }

        @Override
        public myParcelable[] newArray(int size) {
            return new myParcelable[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //数据存储至Parcel
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(sex);
    }

    //方便数据清晰
    @Override
    public String toString() {
        return "myParcelable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
