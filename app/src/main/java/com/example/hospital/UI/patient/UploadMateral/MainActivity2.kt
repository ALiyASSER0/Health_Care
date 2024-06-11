package com.example.hospital.UI.patient.UploadMateral

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.example.hospital.Adapters.AdapterRecyclerFile
import com.example.hospital.Const
import com.example.hospital.R
import com.example.hospital.databinding.ActivityMain2Binding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    private var _binding: ActivityMain2Binding?=null
    private val binding get() = _binding!!
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                uploadFile(it)
            }
        }

    private val mainActivity2ViewModel :  MainActivity2ViewModel by viewModels()
    private lateinit var  adapetr: AdapterRecyclerFile
    private val REQUEST_CODE_STORAGE_PERMISSION = 1001




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.anm.setVisibility(View.GONE)
        binding.progress.setVisibility(View.GONE)

        adapetr=AdapterRecyclerFile {
            Log.e("TAG", "File:${it} ",)
            val originalString = it
            val trimmedString = originalString.substringAfter('/')
            val uriToOpen = Uri.parse("${Const.BASE_URL}${trimmedString}")
            Log.e("TAG", "uriToOpen:${uriToOpen} ",)

            if (uriToOpen.toString().contains("pdf")) {
                val pdfIntent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uriToOpen, "application/pdf")
                    flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                    setPackage("com.google.android.apps.docs") // Package name for Google Drive
                }
                startActivity(pdfIntent)
            }else if(uriToOpen.toString().contains("jpg")){
                val imageIntent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uriToOpen, "image/jpeg")
                    flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                    setPackage("com.google.android.apps.photos") // Package name for Google Photos (common gallery app)
                }
                startActivity(imageIntent)
            }else{
            val intent = Intent(Intent.ACTION_VIEW, uriToOpen)
                startActivity(intent)
            }
        }
        mainActivity2ViewModel.getAllFiles()
        observations()
        binding.btnAddMember.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_STORAGE_PERMISSION
                )
            } else {
                getContent.launch("*/*") // Use the appropriate MIME type
            }

        }


        // Trigger file selection when a button is clicked

    }

    private fun observations() {
        mainActivity2ViewModel.liveData.observe(this){
           if(it.size==0){
               binding.anm.setVisibility(View.VISIBLE)
           }
            adapetr.setMyList(it)
            binding.recyclerMaterial.adapter=adapetr

        }
        mainActivity2ViewModel.errorBody.observe(this){
            binding.anm.setVisibility(View.GONE)
            Log.e("TAG", "reservationsViewModel: $it", )
        }

        mainActivity2ViewModel.liveDataUploadFile.observe(this){
            binding.editEmail.text="Upload PDF"

            binding.anm.setVisibility(View.GONE)
            binding.progress.setVisibility(View.GONE)
            mainActivity2ViewModel.getAllFiles()
            Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with accessing the content URI
            } else {
                // Permission denied, handle accordingly (e.g., show a message or request again)
            }
        }
    }

    private fun uploadFile(fileUri: Uri) {
        binding.editEmail.text=""
        binding.progress.setVisibility(View.VISIBLE)

        val parcelFileDescriptor = contentResolver.openFileDescriptor(fileUri, "r", null)
        val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
        val file = File(cacheDir, getFileName(fileUri))
        Log.e("TAG", "uploadFile:${file} ",)
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        // Create RequestBody instance from file
        val requestFile = file.asRequestBody(contentResolver.getType(fileUri)?.toMediaTypeOrNull())

        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val name = "R".toRequestBody("text/plain".toMediaTypeOrNull())
        // Execute the network request using CoroutineScope


        mainActivity2ViewModel.uploadFile(name, body)


    }


    // Extension function to get file name from Uri
    fun Activity.getFileName(uri: Uri): String {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (it != null && it.moveToFirst()) {
                    result = it.getString(
                        if (it.getColumnIndex(OpenableColumns.DISPLAY_NAME) > 0) {
                            it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        } else {
                            1
                        }
                    )
                }
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result?.lastIndexOf('/')
            if (cut != -1) {
                if (cut != null) {
                    result = result?.substring(cut + 1)
                }
            }
        }
        return result ?: "unknown"
    }


}
