package ru.cft.shift2023winter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.shift2023winter.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	private val navController get() = findNavController(R.id.fragment_container)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}