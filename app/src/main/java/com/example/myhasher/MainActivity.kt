package com.example.myhasher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val input = findViewById<EditText>(R.id.editTextTextPersonName)
        val output = findViewById<EditText>(R.id.editTextTextMultiLine)
        val algo = findViewById<Spinner>(R.id.spinner)
        val button1 = findViewById<Button>(R.id.button)
        button1?.setOnClickListener() {
            //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
            //val selected_index = algo.selectedItemPosition
            output.setText("")
            if (input.getText().toString().equals(""))
                Toast.makeText(this, R.string.input_empty, Toast.LENGTH_SHORT).show()
            else if (algo.selectedItemPosition == 0)
                output.setText(HashUtils.sha1(input.getText().toString()))
            else if (algo.selectedItemPosition == 1)
                output.setText(HashUtils.sha256(input.getText().toString()))
            else if (algo.selectedItemPosition == 2)
                output.setText(HashUtils.sha512(input.getText().toString()))
        }
    }


    /**
     * Hashing Utils
     * @author Sam Clarke <www.samclarke.com>
     * @license MIT
     */
    object HashUtils {
        fun sha512(input: String) = hashString("SHA-512", input)

        fun sha256(input: String) = hashString("SHA-256", input)

        fun sha1(input: String) = hashString("SHA-1", input)

        /**
         * Supported algorithms on Android:
         *
         * Algorithm	Supported API Levels
         * MD5          1+
         * SHA-1	    1+
         * SHA-224	    1-8,22+
         * SHA-256	    1+
         * SHA-384	    1+
         * SHA-512	    1+
         */
        private fun hashString(type: String, input: String): String {
            val HEX_CHARS = "0123456789ABCDEF"
            val bytes = MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
            val result = StringBuilder(bytes.size * 2)

            bytes.forEach {
                val i = it.toInt()
                result.append(HEX_CHARS[i shr 4 and 0x0f])
                result.append(HEX_CHARS[i and 0x0f])
            }

            return result.toString()
        }
    }

}