package com.be.mypals.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.be.mypals.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink;
    ProgressBar _progressBar;
    Connection conn;
    String un, pass, db, ip;

    public AccountFragment() {
        // Required empty public constructor
    }
    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_account, container, false);
        _emailText = RootView.findViewById(R.id.input_email);
        _passwordText = RootView.findViewById(R.id.input_password);
        _loginButton = RootView.findViewById(R.id.btn_login);
        _signupLink = RootView.findViewById(R.id.link_signup);
        _progressBar = RootView.findViewById(R.id.progressBar);
        _progressBar.setVisibility(View.GONE);


        //declaring db details
        ip = "localhost:81/";
        db = "pals_test";
        un = "root";
        pass = "";

        _loginButton.setOnClickListener((v) -> {
            CheckLogin checkLogin = new CheckLogin();
            checkLogin.execute("");
        });


        return RootView;
    }

    public class CheckLogin extends AsyncTask<String, String, String>{
        String s = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            _progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String email = _emailText.getText().toString();
            String password = _passwordText.getText().toString();
            if (email.trim().equals("") || password.trim().equals("")) {
                s = "Please enter username and password";
            } else {
                try {
                    conn = connectionclass(un, pass, db, ip);
                    if (conn == null) {
                        s = "Check your internet access";
                    } else {
                        String query = "Select * from login where email = '" + email.toString() + "' and password = '" + password.toString();
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            s = "Login successful";
                            isSuccess = true;
                            conn.close();
                        } else {
                            s = "Invalid credentials";
                            isSuccess= false;
                        }
                    }
                } catch (Exception e) {
                    isSuccess = false;
                    s = e.getMessage();

                }
            }
            return s;
        }


        @Override
        protected void onPostExecute(String s) {
            _progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionclass (String user, String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server + database + "/user=" + user + "/password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);
        } catch (SQLException se) {
            Log.e("error 1 here: ", se.getMessage());
        } catch (ClassNotFoundException se) {
            Log.e("error 2 here: ", se.getMessage());
        } catch (Exception se) {
            Log.e("error 3 here: ", se.getMessage());
        }
        return connection;
    }

}
