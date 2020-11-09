package androidmads.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GenDataActivity extends AppCompatActivity {

    EditText editTextName, editTextAddress, editTextPhone, editTextAddressMail, editTextNotes, editTextOrganization, editTextURL;
    Button btnGenerate;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_data);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddressMail = findViewById(R.id.editTextAddressMail);
        btnGenerate = findViewById(R.id.btnGenerate);
        editTextNotes = findViewById(R.id.editTextNotes);
        editTextOrganization = findViewById(R.id.editTextOrganization);
        editTextURL = findViewById(R.id.editTextURL);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //keys in bundle correspond to the fields in the ContactsContract.class
                Intent intent = new Intent(getApplicationContext(), GenQRActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", editTextName.getText().toString());
                bundle.putString("postal", editTextAddress.getText().toString());
                bundle.putString("phone", editTextPhone.getText().toString());
                bundle.putString("email", editTextAddressMail.getText().toString());
                bundle.putString("notes", editTextNotes.getText().toString());
                bundle.putString("company", editTextOrganization.getText().toString());
                bundle.putString("data", editTextURL.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}