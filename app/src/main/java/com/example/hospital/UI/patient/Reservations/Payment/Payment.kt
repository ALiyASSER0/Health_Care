package com.example.hospital.UI.patient.Reservations.Payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hospital.databinding.FragmentPaymentBinding


class Payment : Fragment() {
    private var _binding: FragmentPaymentBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentPaymentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    val originalText = it.toString().replace("\\s".toRegex(), "") // Remove any spaces
                    val formattedText = StringBuilder()
                    for (i in originalText.indices) {
                        if (i > 0 && i % 4 == 0) {
                            formattedText.append(" ")
                        }
                        formattedText.append(originalText[i])
                    }

                    binding.editText.removeTextChangedListener(this)
                    binding.editText.setText(formattedText.toString())
                    binding.editText.setSelection(formattedText.length)
                    binding.editText.addTextChangedListener(this)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.expire.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Not needed for this example
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                s?.let {
                    val originalText =
                        it.toString().replace("\\s".toRegex(), "") // Remove any spaces
                    if (originalText.length == 1) {
                        val format = originalText.substring(0, 1)
                        val firstDigit = format[0].digitToInt()
                        if (firstDigit > 1) {
                            binding.expire.error = "Invalid Month"
                        } else {
                            binding.expire.removeTextChangedListener(this)
                            binding.expire.setText(format.toString())
                            binding.expire.setSelection(format.length)
                            binding.expire.addTextChangedListener(this)
                        }
                    } else if (originalText.length == 2) {
                        val format = originalText.substring(0, 2)
                        val firstDigit = format[0].digitToInt()
                        val secondDigit = format[1].digitToInt()
                        if (firstDigit > 1) {
                            binding.expire.error = "Invalid Month"
                        } else {
                            if (firstDigit==1 && secondDigit > 2) {
                                binding.expire.error = "Invalid Month"
                            }

                        }
                        binding.expire.removeTextChangedListener(this)
                        binding.expire.setText(originalText.toString())
                        binding.expire.setSelection(originalText.length)
                        binding.expire.addTextChangedListener(this)

                    } else if (originalText.length == 3) {
                        var format = ""
                        if (originalText.contains("/")) {

                            format = originalText.substring(0, 3)


                        } else {
                            val firstDigit = originalText[0].digitToInt()
                            val secondDigit = originalText[1].digitToInt()
                            val thirdDigit = originalText[2].digitToInt()
                            if (firstDigit > 1 || secondDigit > 2||(firstDigit==0&&secondDigit==0)) {
                                binding.expire.error = "Enter correct month"

                            } else if (thirdDigit !in 2..3) {
                                binding.expire.error = "Invalid Year"
                                format =
                                    originalText.substring(0, 2) + "/" + originalText.substring(2)
                            } else {
                                format =
                                    originalText.substring(0, 2) + "/" + originalText.substring(2)
                            }

                        }
                        binding.expire.removeTextChangedListener(this)
                        binding.expire.setText(format)
                        binding.expire.setSelection(format.length)
                        binding.expire.addTextChangedListener(this)


                    }

                    else if (originalText.length == 4) {
                        var format = ""
                        var fourDigit = originalText[3].digitToInt()
                        if (fourDigit !in 2..3) {
                            binding.expire.error = "Invalid Year"
                            format = originalText.substring(0, 2) + originalText.substring(2)
                        } else {
                            format = originalText.substring(0, 2)  + originalText.substring(2)
                        }
                        binding.expire.removeTextChangedListener(this)
                        binding.expire.setText(format)
                        binding.expire.setSelection(format.length)
                        binding.expire.addTextChangedListener(this)
                    }
                    else if (originalText.length == 5) {
                        var format = ""
                        var thirdDigit = originalText[3].digitToInt()
                        var fourDigit = originalText[4].digitToInt()
                         if (thirdDigit !in 2..3) {
                            binding.expire.error = "Invalid Year"
                            format =
                                originalText.substring(0, 2) + originalText.substring(2)
                        }else if (fourDigit !in 4..9) {
                            binding.expire.error = "Invalid Year"
                            format = originalText.substring(0, 2) + originalText.substring(2)
                        } else {
                            format = originalText.substring(0, 2)  + originalText.substring(2)
                        }
                        binding.expire.removeTextChangedListener(this)
                        binding.expire.setText(format)
                        binding.expire.setSelection(format.length)
                        binding.expire.addTextChangedListener(this)
                    }


                  else{
                        binding.expire.removeTextChangedListener(this)
                        binding.expire.setText(originalText.toString())
                        binding.expire.setSelection(originalText.length)
                        binding.expire.addTextChangedListener(this)
                    }

                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        });
    }
}
