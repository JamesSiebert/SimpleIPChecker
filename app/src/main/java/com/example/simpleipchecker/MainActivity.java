package com.example.simpleipchecker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {
    EditText ip_address_input;
    TextView ip_address_status_text;
    String ip_address;
    Button check_ip_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // An example of how to make popup notifications
        Toast.makeText(getApplicationContext(), "Test Toast", Toast.LENGTH_SHORT).show();

        // This removes the errors. Error is because everything is running on the same thread.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Find and link input text
        ip_address_input = findViewById(R.id.ip_address_input);

        // Find and link results text
        ip_address_status_text = findViewById(R.id.ip_address_status_text);

        // Find and link check button
        check_ip_button = findViewById(R.id.button_check_ip);

        // Create button listener - this calls when the button is clicked
        check_ip_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses Check IP button

                // read the IP address provided
                ip_address = ip_address_input.getText().toString();

                try {
                    // Check the IP address
                    boolean reachable = InetAddress.getByName(ip_address).isReachable(1000);

                    if (reachable) {
                        // Create the message to return
                        String message = "Found " + ip_address;
                        // Set the message
                        ip_address_status_text.setText(message);
                    } else {
                        // Create the message to return
                        String message = "Could not find " + ip_address;
                        // Set the message
                        ip_address_status_text.setText(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
