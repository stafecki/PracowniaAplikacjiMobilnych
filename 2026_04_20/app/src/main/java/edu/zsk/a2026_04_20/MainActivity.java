package edu.zsk.a2026_04_20;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private final int[] imageResources = {
            R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
            R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
            R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
            R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
            R.drawable.photo1, R.drawable.photo2, R.drawable.photo3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LinearLayout galleryContainer = findViewById(R.id.galleryContainer);

        int sizePx = dpToPx(160);
        int marginPx = dpToPx(8);

        for (int i = 0; i < imageResources.length; i++) {
            final int resId = imageResources[i];
            final int position = i + 1;

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizePx, sizePx);
            params.setMargins(marginPx, marginPx, marginPx, marginPx);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(resId);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getSupportFragmentManager();
                    PhotoDialogFragment dialog = PhotoDialogFragment.newInstance(resId, position);
                    dialog.show(fm, "photo_dialog");
                }
            });

            galleryContainer.addView(imageView);
        }
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    // -------------------------------------------------------
    // DialogFragment jako klasa wewnętrzna
    // -------------------------------------------------------
    public static class PhotoDialogFragment extends DialogFragment {

        private static final String ARG_RES_ID = "res_id";
        private static final String ARG_POSITION = "position";

        public static PhotoDialogFragment newInstance(int resId, int position) {
            PhotoDialogFragment fragment = new PhotoDialogFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_RES_ID, resId);
            args.putInt(ARG_POSITION, position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int resId = getArguments().getInt(ARG_RES_ID);

            ImageView imageView = new ImageView(requireContext());
            imageView.setImageResource(resId);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setAdjustViewBounds(true);

            return new AlertDialog.Builder(requireActivity())
                    .setView(imageView)
                    .setPositiveButton("Zamknij", (d, w) -> dismiss())
                    .create();
        }
    }
}