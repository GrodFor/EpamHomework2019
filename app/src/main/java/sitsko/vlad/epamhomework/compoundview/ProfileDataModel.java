package sitsko.vlad.epamhomework.compoundview;

import android.support.annotation.DrawableRes;

public class ProfileDataModel {

    private String name;
    private String mail;

    @DrawableRes
    private int icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
