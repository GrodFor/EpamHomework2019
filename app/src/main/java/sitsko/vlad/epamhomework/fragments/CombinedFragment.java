package sitsko.vlad.epamhomework.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sitsko.vlad.epamhomework.R;

public class CombinedFragment extends Fragment {

    private static final String NUMBER_BUNDLE_KEY = "NUMBER_KEY";

    private TextView idTextView;
    private String receivedString;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_combined, container, false);
        idTextView = rootView.findViewById(R.id.fragment_text);

        if (getArguments() != null) {
            receivedString = getArguments().getString(NUMBER_BUNDLE_KEY);
        } else {
            receivedString = "Default";
        }

        idTextView.setText(receivedString);

        return rootView;
    }
}
