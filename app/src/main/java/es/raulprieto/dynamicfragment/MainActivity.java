package es.raulprieto.dynamicfragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentA.OnSetTextSizeListener {
    private static final String TAG = "DynamicFragment";
    Fragment fragmentA;
    Fragment fragmentB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Avoid creating multiple fragments when turning the phone screen
        fragmentA = getSupportFragmentManager().findFragmentByTag(FragmentA.TAG);
        if (fragmentA == null) {
            fragmentA = new FragmentA();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(android.R.id.content, fragmentA, FragmentA.TAG);

            fragmentTransaction.commit();
        }

    }

    @Override
    public void onSetTextSize(String text, int size) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();


        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        bundle.putFloat("size", size);
        fragmentB = FragmentB.newInstance(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(android.R.id.content, fragmentB, FragmentB.TAG);
        // It is stored the transaction state into the fragments pile stored by the Activity
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
