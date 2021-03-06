package com.example.garam.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.squti.androidwaverecorder.WaveRecorder

class AudioRecord : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_record)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), 100)
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }
        Toast.makeText(this,"버튼을 누르고 화면에 표시된 글자를 읽어주세요",Toast.LENGTH_LONG).show()
        val recordbutton = findViewById<Button>(R.id.button4)
        val okbutton = findViewById<Button>(R.id.button5)
        val daebon = findViewById<TextView>(R.id.daebon)
        daebon.text = "이 많은 별빛이 내린 언덕 위에\n" + "그대만큼 사랑스러운 사람을 본 일이 없다"
        val path = cacheDir.path + "/" + "recorder.wav"
        val waveRecorder = WaveRecorder(path)
        okbutton.isEnabled = false
        recordbutton.setOnClickListener {
           Toast.makeText(this,"녹음을 시작합니다",Toast.LENGTH_SHORT).show()
            okbutton.isEnabled = true
           // waveRecorder.waveConfig.sampleRate = 44100
           // waveRecorder.waveConfig.channels = AudioFormat.CHANNEL_IN_STEREO
           // waveRecorder.waveConfig.audioEncoding = AudioFormat.ENCODING_PCM_8BIT
            waveRecorder.noiseSuppressorActive = true
            waveRecorder.startRecording()
        }
        okbutton.setOnClickListener {
            waveRecorder.stopRecording()
            Toast.makeText(this,"녹음 완료",Toast.LENGTH_LONG).show()
            finish()
        }
    }


}
