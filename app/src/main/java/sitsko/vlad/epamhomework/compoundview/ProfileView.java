package sitsko.vlad.epamhomework.compoundview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import sitsko.vlad.epamhomework.R;

public class ProfileView extends LinearLayout {

    private TextView profileNameView;
    private TextView profileMailView;
    private ImageView profileIcon;

    public ProfileView(Context context) {
        super(context);
        init();
    }

    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);

        inflate(getContext(), R.layout.profile_view, this);

        profileIcon = findViewById(R.id.profile_image);
        profileNameView = findViewById(R.id.profile_name);
        profileMailView = findViewById(R.id.profile_mail);
    }

    public void updateUserInfo(ProfileDataModel profileDataModel) {
        profileNameView.setText(profileDataModel.getName());
        profileMailView.setText(profileDataModel.getMail());

        Drawable drawable = ContextCompat.getDrawable(getContext(), profileDataModel.getIcon());
        profileIcon.setImageDrawable(drawable);
    }
}
