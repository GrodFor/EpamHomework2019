package sitsko.vlad.epamhomework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeworkFragment extends Fragment {

    private static final String COUNTER_TEXT_KEY = "resultTextView";
    private static final String TAG = HomeworkFragment.class.getSimpleName();
    private static final String ZERO = "0";

    private TextView resultTextView;
    private ImageButton decrementButton;
    private ImageButton incrementButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homework, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        resultTextView = view.findViewById(R.id.result_text);
        decrementButton = view.findViewById(R.id.decrement_button);
        incrementButton = view.findViewById(R.id.increment_button);

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTextEmpty();
                int currentValue = Integer.parseInt(resultTextView.getText().toString());
                resultTextView.setText(String.valueOf(currentValue - 1));
            }
        });

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTextEmpty();
                int currentValue = Integer.parseInt(resultTextView.getText().toString());
                resultTextView.setText(String.valueOf(currentValue + 1));
            }
        });
        Log.d(TAG, "onViewCreated: ");

        if (savedInstanceState != null) {
            String value = savedInstanceState.getString(COUNTER_TEXT_KEY);
            resultTextView.setText(value);
            Log.d(TAG, "savedInstanceState: " + value);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String value = resultTextView.getText().toString();
        outState.putString(COUNTER_TEXT_KEY, value);
        Log.d(TAG, "onSaveInstanceState: " + value);
    }

    private void isTextEmpty() {
        if (resultTextView.getText().toString().isEmpty()) {
            resultTextView.setText(ZERO);
        }
    }
}
